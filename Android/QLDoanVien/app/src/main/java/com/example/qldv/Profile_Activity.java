package com.example.qldv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile_Activity extends AppCompatActivity {
    Button btnBack;
    ImageView avatarpath;

    TextView chucvu, tructhuoc, khoa, emaildv, sdtdv, ngaysinh, gioitinh, dantoc, ngayvaodoan, tendoanvien, madoanvien;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        LoadData();
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void LoadData() {
        String home_namedv = getIntent().getStringExtra("name");
        String home_image = getIntent().getStringExtra("image");
        String home_chucvu = getIntent().getStringExtra("chucvu");
        String home_chidoan = getIntent().getStringExtra("chidoan");
        String home_khoa = getIntent().getStringExtra("khoa");
        String home_madv = getIntent().getStringExtra("madv");
        String home_email = getIntent().getStringExtra("email");
        String home_sdt = getIntent().getStringExtra("dienthoai");
        String home_dob = getIntent().getStringExtra("dob");
        String home_gender = getIntent().getStringExtra("gender");
        String home_dantoc = getIntent().getStringExtra("danToc");
        String home_ngayvaodoan = getIntent().getStringExtra("ngayvaodoan");
        chucvu = findViewById(R.id.frm_chucvu);
        tructhuoc = findViewById(R.id.trucThuoc);
        khoa = findViewById(R.id.Khoa);
        emaildv = findViewById(R.id.emaildv);
        sdtdv = findViewById(R.id.sdtDv);
        ngaysinh = findViewById(R.id.ngaySinh);
        gioitinh = findViewById(R.id.gioiTinh);
        dantoc = findViewById(R.id.danToc);
        ngayvaodoan = findViewById(R.id.ngayvaoDoan);
        tendoanvien = findViewById(R.id.tenDoanvien);
        madoanvien = findViewById(R.id.madv);
        avatarpath = findViewById(R.id.imageprofile);

        chucvu.setText(home_chucvu);
        tructhuoc.setText(home_chidoan);
        khoa.setText(home_khoa);
        emaildv.setText(home_email);
        sdtdv.setText(home_sdt);
        ngaysinh.setText(home_dob);
        if ("true".equalsIgnoreCase(home_gender)) {
            gioitinh.setText("Nam");
        } else {
            gioitinh.setText("Nữ");

        }
        dantoc.setText(home_dantoc);
        ngayvaodoan.setText(home_ngayvaodoan);
        tendoanvien.setText(home_namedv);
        madoanvien.setText(home_madv);
        Glide.with(this).load(home_image).circleCrop().into(avatarpath);
    }
}