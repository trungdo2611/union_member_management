package com.example.qldv;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class Home_Activity extends AppCompatActivity {
    TextView tendv;
    ImageView Logout, avatarpath;
    CardView Listdv, ThongtinDV, NoidungDV;

    BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tendv = findViewById(R.id.tenDoanvien);
        avatarpath = findViewById(R.id.avatar);
        Logout = findViewById(R.id.logout);
        ThongtinDV = findViewById(R.id.thongtindv);
        NoidungDV = findViewById(R.id.noidungdv);
        Bundle extras = getIntent().getExtras();
        String namedv = getIntent().getStringExtra("tendoanvien");
        String imagepath = getIntent().getStringExtra("hinhAnh");
        String login_madv = getIntent().getStringExtra("maDoanVien");
        String login_ngaysinh = getIntent().getStringExtra("ngaySinh");
        String login_gioitinh = getIntent().getStringExtra("gioiTinh");
        String login_email = getIntent().getStringExtra("email");
        String login_sodienthoai = getIntent().getStringExtra("dienThoai");
        String login_ngayvaodoan = getIntent().getStringExtra("ngayVaoDoan");
        String login_chucvu = getIntent().getStringExtra("tenChucVu");
        String login_chidoan = getIntent().getStringExtra("tenChiDoan");
        String login_khoa = getIntent().getStringExtra("tenKhoa");
        String login_dantoc = getIntent().getStringExtra("danToc");
        String login_iddv = getIntent().getStringExtra("id");

        tendv.setText(namedv);
        Glide.with(this).load(imagepath).circleCrop().into(avatarpath);

        ThongtinDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile_Activity.class);
                intent.putExtra("madv", login_madv);
                intent.putExtra("name", namedv);
                intent.putExtra("image", imagepath);
                intent.putExtra("dob", login_ngaysinh);
                intent.putExtra("gender", login_gioitinh);
                intent.putExtra("email", login_email);
                intent.putExtra("dienthoai", login_sodienthoai);
                intent.putExtra("ngayvaodoan", login_ngayvaodoan);
                intent.putExtra("chucvu", login_chucvu);
                intent.putExtra("chidoan", login_chidoan);
                intent.putExtra("khoa", login_khoa);
                intent.putExtra("danToc", login_dantoc);
                Log.d("imageLocal_home", "" + imagepath + "," + login_madv
                        + "," + login_ngaysinh + "," + login_gioitinh + "," + login_email + "," + login_sodienthoai + "," + login_ngayvaodoan + "," + login_chucvu
                        + "," + login_chidoan + "," + login_khoa + "," + login_dantoc + "," + login_iddv
                );
                startActivity(intent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Activity.this,
                        MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        NoidungDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Note_Activity.class);
                intent.putExtra("id", login_iddv);
                Log.d("login_iddv", login_iddv);
                startActivity(intent);
            }
        });
        Listdv = findViewById(R.id.ListDV);
        Listdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListDV_Activity.class);
                startActivity(intent);
            }
        });
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homepage) {
//                    Intent homeIntent = new Intent(Home_Activity.this, Home_Activity.class);
//                    startActivity(homeIntent);
                } else if (item.getItemId() == R.id.lawpage) {
                    Intent lawIntent = new Intent(Home_Activity.this, NoiQuy_Activity.class);
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
                    Intent settingIntent = new Intent(Home_Activity.this, SettingPage_Activity.class);
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