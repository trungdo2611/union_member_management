package com.example.qldv.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.qldv.Profile_Activity;
import com.example.qldv.R;
import java.util.ArrayList;

public class DoanVienAdapter extends RecyclerView.Adapter<DoanVienAdapter.ViewHolder> {
    private ArrayList<DoanVien> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    // Constructor
    public DoanVienAdapter(Context context, ArrayList<DoanVien> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Phương thức onCreateViewHolder được gọi khi RecyclerView cần một ViewHolder mới
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.itemdoanvien, parent, false);
        return new ViewHolder(view);
    }

    // Phương thức onBindViewHolder được gọi khi RecyclerView muốn hiển thị dữ liệu tại vị trí đã chỉ định
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoanVien doanVien = mData.get(position);
        holder.tendv.setText(doanVien.getTenDv());
        holder.chidoandv.setText(doanVien.getChiDoan());
        holder.khoadv.setText(doanVien.getKhoa());
        Glide.with(mContext).load(doanVien.getImagepath()).circleCrop().into(holder.imagedv);

        // Xử lý sự kiện click cho biểu tượng hồ sơ
        holder.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfile = new Intent(mContext, Profile_Activity.class);
                gotoProfile.putExtra("name", doanVien.getTenDv());
                gotoProfile.putExtra("image", doanVien.getImagepath());
                gotoProfile.putExtra("chucvu", doanVien.getChucVu());
                gotoProfile.putExtra("chidoan", doanVien.getChiDoan());
                gotoProfile.putExtra("khoa", doanVien.getKhoa());
                gotoProfile.putExtra("madv", doanVien.getMaDv());
                gotoProfile.putExtra("email", doanVien.getMail());
                gotoProfile.putExtra("dienthoai", doanVien.getSdt());
                gotoProfile.putExtra("dob", doanVien.getDob());
                gotoProfile.putExtra("gender", doanVien.getGenDer());
                gotoProfile.putExtra("danToc", doanVien.getDanToc());
                gotoProfile.putExtra("ngayvaodoan", doanVien.getNgayvaodoan());
                mContext.startActivity(gotoProfile);
            }
        });
    }

    // Phương thức getItemCount trả về tổng số item trong danh sách
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Lớp ViewHolder lưu trữ và tái sử dụng các view khi chúng không hiển thị trên màn hình
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tendv, chidoandv, khoadv;
        ImageView imagedv, search;

        ViewHolder(View itemView) {
            super(itemView);
            tendv = itemView.findViewById(R.id.dv_name);
            chidoandv = itemView.findViewById(R.id.dv_chidoan);
            khoadv = itemView.findViewById(R.id.dv_khoa);
            imagedv = itemView.findViewById(R.id.imgEdit);
            search = itemView.findViewById(R.id.search_icon);
        }
    }
}
