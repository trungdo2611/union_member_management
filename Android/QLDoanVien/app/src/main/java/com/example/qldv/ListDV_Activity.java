package com.example.qldv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.qldv.model.DoanVien;
import com.example.qldv.model.DoanVienAdapter;
import com.example.qldv.model.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListDV_Activity extends AppCompatActivity {
    RecyclerView listDv;
    Button btnBack;
    DoanVienAdapter numbersArrayAdapter;


    ArrayList<DoanVien> danhsachDv = new ArrayList<>();
    EditText editTextSearch;
    String ipAddress = "192.168.59.1";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dv);

        listDv = findViewById(R.id.listdv);
        listDv.setLayoutManager(new LinearLayoutManager(this));
        editTextSearch = findViewById(R.id.seacrhtext);
        numbersArrayAdapter = new DoanVienAdapter(this, danhsachDv); // Khởi tạo adapter và gán cho RecyclerView
        listDv.setAdapter(numbersArrayAdapter); // Gán adapter cho RecyclerView
        LoadDataToListDv(editTextSearch.getText().toString());
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    LoadDataToListDv(editTextSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void LoadDataToListDv(String searchtext) {

        RequestQueue queue = Volley.newRequestQueue(ListDV_Activity.this);
        String url = "http://" + ipAddress + ":8080/doanvien/listdoanvien?search=" + searchtext;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    danhsachDv.clear();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject item = response.getJSONObject(i);
//                        String id,maDv,tenDv,dob,genDer,danToc,mail,sdt,ngayvaodoan,imagepath,chucVu,chiDoan,Khoa;
                        int id = item.getInt("id");
                        String idStr = String.valueOf(id);
                        String maDv = item.getString("maDoanVien");
                        String tenDv = item.getString("tenDoanVien");
                        String dob = item.getString("ngaySinh");
                        Boolean genDer = item.getBoolean("gioiTinh");
                        String genDerStr = genDer.toString();
                        String danToc = item.getString("danToc");
                        String mail = item.getString("email");
                        String sdt = item.getString("dienThoai");
                        String ngayvaodoan = item.getString("ngayVaoDoan");
                        String imagepath = item.getString("hinhAnh");
                        String imageLocalUpdate = imagepath.replace("localhost", ipAddress);
                        String chucVu = item.getString("tenChucVu");
                        String chiDoan = item.getString("tenChiDoan");
                        String Khoa = item.getString("tenKhoa");
                        DoanVien dv = new DoanVien(idStr, maDv, tenDv, dob, genDerStr, danToc, mail, sdt, ngayvaodoan, imageLocalUpdate, chucVu, chiDoan, Khoa);
                        danhsachDv.add(dv);

                    }
                    for (int i = 0; i < danhsachDv.size(); i++) {
                        DoanVien currentDoanVien = danhsachDv.get(i);
                        Log.d("DoanVien", "Index " + i + ": " + currentDoanVien.getId() + ", " + currentDoanVien.getMaDv() + ", " + currentDoanVien.getTenDv() + ", " + currentDoanVien.getDob() + ", " + currentDoanVien.getGenDer() + ", " + currentDoanVien.getDanToc() + ", " + currentDoanVien.getMail() + ", " + currentDoanVien.getSdt() + ", " + currentDoanVien.getNgayvaodoan() + ", " + currentDoanVien.getImagepath() + ", " + currentDoanVien.getChucVu() + ", " + currentDoanVien.getChiDoan() + ", " + currentDoanVien.getKhoa());
                    }
                    numbersArrayAdapter.notifyDataSetChanged();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    Toast.makeText(ListDV_Activity.this, "Lỗi xử lý dữ liệu JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Lỗi khi gửi yêu cầu GET: " + error.toString());
                Toast.makeText(ListDV_Activity.this, "Lỗi khi gửi yêu cầu GET", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);

    }
}