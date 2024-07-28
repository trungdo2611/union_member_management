package com.example.qldv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingPage_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String namedv, imagepath, login_madv, login_ngaysinh, login_gioitinh, login_email, login_sodienthoai, login_ngayvaodoan, login_chucvu, login_chidoan, login_khoa, login_dantoc, login_iddv;
    CardView changePassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        // Khai báo và gán giá trị cho các biến
        namedv = getIntent().getStringExtra("name");
        imagepath = getIntent().getStringExtra("image");
        login_madv = getIntent().getStringExtra("madv");
        login_ngaysinh = getIntent().getStringExtra("dob");
        login_gioitinh = getIntent().getStringExtra("gender");
        login_email = getIntent().getStringExtra("email");
        login_sodienthoai = getIntent().getStringExtra("dienthoai");
        login_ngayvaodoan = getIntent().getStringExtra("ngayvaodoan");
        login_chucvu = getIntent().getStringExtra("chucvu");
        login_chidoan = getIntent().getStringExtra("chidoan");
        login_khoa = getIntent().getStringExtra("khoa");
        login_dantoc = getIntent().getStringExtra("danToc");
        login_iddv = getIntent().getStringExtra("id");
        changePassWord = findViewById(R.id.ChangePassWord);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homepage) {
                    Intent homeIntent = new Intent(SettingPage_Activity.this, Home_Activity.class);
                    homeIntent.putExtra("tendoanvien", namedv);
                    homeIntent.putExtra("hinhAnh", imagepath);
                    homeIntent.putExtra("maDoanVien", login_madv);
                    homeIntent.putExtra("ngaySinh", login_ngaysinh);
                    homeIntent.putExtra("gioiTinh", login_gioitinh);
                    homeIntent.putExtra("email", login_email);
                    homeIntent.putExtra("dienThoai", login_sodienthoai);
                    homeIntent.putExtra("ngayVaoDoan", login_ngayvaodoan);
                    homeIntent.putExtra("tenChucVu", login_chucvu);
                    homeIntent.putExtra("tenChiDoan", login_chidoan);
                    homeIntent.putExtra("tenKhoa", login_khoa);
                    homeIntent.putExtra("danToc", login_dantoc);
                    homeIntent.putExtra("id", login_iddv);
                    overridePendingTransition(0,0);
                    startActivity(homeIntent);

                } else if (item.getItemId() == R.id.lawpage) {
                    Intent lawIntent = new Intent(SettingPage_Activity.this, NoiQuy_Activity.class);
                    lawIntent.putExtra("madv", login_madv);
                    lawIntent.putExtra("name", namedv);
                    lawIntent.putExtra("image", imagepath);
                    lawIntent.putExtra("dob", login_ngaysinh);
                    lawIntent.putExtra("gender", login_gioitinh);
                    lawIntent.putExtra("email", login_email);
                    lawIntent.putExtra("dienthoai", login_sodienthoai);
                    lawIntent.putExtra("ngayvaodoan", login_ngayvaodoan);
                    lawIntent.putExtra("chucvu", login_chucvu);
                    lawIntent.putExtra("chidoan", login_chidoan);
                    lawIntent.putExtra("khoa", login_khoa);
                    lawIntent.putExtra("danToc", login_dantoc);
                    lawIntent.putExtra("id", login_iddv);
                    overridePendingTransition(0,0);
                    startActivity(lawIntent);
                } else if (item.getItemId() == R.id.settingpage) {

                }

                return true;
            }
        });
        changePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changpassIntent = new Intent(SettingPage_Activity.this, Change_Activity.class);
                changpassIntent.putExtra("id", login_iddv);
                overridePendingTransition(0,0);
                startActivity(changpassIntent);
            }
        });
    }
}