package com.example.qldv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NoiQuy_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String namedv, imagepath, login_madv, login_ngaysinh, login_gioitinh, login_email, login_sodienthoai, login_ngayvaodoan, login_chucvu, login_chidoan, login_khoa, login_dantoc, login_iddv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_quy);
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
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homepage) {
                    Intent homeIntent = new Intent(NoiQuy_Activity.this, Home_Activity.class);
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

                } else if (item.getItemId() == R.id.settingpage) {
                    Intent settingIntent = new Intent(NoiQuy_Activity.this, SettingPage_Activity.class);
                    settingIntent.putExtra("madv", login_madv);
                    settingIntent.putExtra("name", namedv);
                    settingIntent.putExtra("image", imagepath);
                    settingIntent.putExtra("dob", login_ngaysinh);
                    settingIntent.putExtra("gender", login_gioitinh);
                    settingIntent.putExtra("email", login_email);
                    settingIntent.putExtra("dienthoai", login_sodienthoai);
                    settingIntent.putExtra("ngayvaodoan", login_ngayvaodoan);
                    settingIntent.putExtra("chucvu", login_chucvu);
                    settingIntent.putExtra("chidoan", login_chidoan);
                    settingIntent.putExtra("khoa", login_khoa);
                    settingIntent.putExtra("danToc", login_dantoc);
                    settingIntent.putExtra("id", login_iddv);
                    overridePendingTransition(0,0);
                    startActivity(settingIntent);

                }

                return true;
            }
        });
    }
}