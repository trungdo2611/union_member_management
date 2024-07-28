package com.example.qldv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    String ipAddress = "192.168.59.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "http://"+ipAddress+":8080/auth/login";
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("email", username.getText().toString());
                params.put("password", password.getText().toString());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            int idInt = response.getInt("id");
                            String iddv = String.valueOf(idInt);
                            String name = (String) response.get("tenDoanVien");
                            String imageLocal = (String) response.get("hinhAnh");
                            String madv = (String) response.get("maDoanVien");
                            String dob =  (String) response.get("ngaySinh");
                            Boolean gender =  (Boolean) response.get("gioiTinh");
                            String genderString = gender.toString();
                            String mail =  (String) response.get("email");
                            String sdt =  (String) response.get("dienThoai");
                            String ngayvaodoan = (String) response.get("ngayVaoDoan");
                            String chucvu = (String) response.get("tenChucVu");
                            String chidoan = (String) response.get("tenChiDoan");
                            String khoa = (String) response.get("tenKhoa");
                            String dantoc = (String) response.get("danToc");
                            String imageLocalUpdate = imageLocal.replace("localhost",ipAddress);
                            Intent gotoHome = new Intent(MainActivity.this, Home_Activity.class);
                            gotoHome.putExtra("tendoanvien", name);
                            gotoHome.putExtra("hinhAnh",imageLocalUpdate);
                            gotoHome.putExtra("maDoanVien",madv);
                            gotoHome.putExtra("ngaySinh",dob);
                            gotoHome.putExtra("gioiTinh",genderString);
                            gotoHome.putExtra("email",mail);
                            gotoHome.putExtra("dienThoai",sdt);
                            gotoHome.putExtra("ngayVaoDoan",ngayvaodoan);
                            gotoHome.putExtra("tenChucVu",chucvu);
                            gotoHome.putExtra("tenChiDoan",chidoan);
                            gotoHome.putExtra("tenKhoa",khoa);
                            gotoHome.putExtra("danToc",dantoc);
                            gotoHome.putExtra("id",iddv);

                            Log.d("namedv", name);
                            Log.d("imageLocal",""+ imageLocalUpdate+","+madv
                            +","+dob+","+gender+","+mail+","+sdt+","+ngayvaodoan+","+chucvu
                                    +","+chidoan+","+ khoa+","+dantoc+","+iddv
                            );


                            startActivity(gotoHome);


                        } catch (JSONException ex) {
                            ex.printStackTrace();
                            System.out.println(ex.getMessage());
                            Log.d("Error nhan duoc", ex.getMessage());

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.getMessage() != null) {
                            Log.d("Error thong tin", error.getMessage());
                            // Hiển thị thông báo Toast khi tên đăng nhập hoặc mật khẩu không chính xác
                            Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("Error thong tin", "Unknown error");
                            Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
    }
}