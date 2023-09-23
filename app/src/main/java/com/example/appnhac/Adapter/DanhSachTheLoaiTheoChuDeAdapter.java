package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.DanhSachBaiHatActivity;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder>{

    Context context;
    ArrayList<TheLoai> mangTheLoai;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> mangTheLoai) {
        this.context = context;
        this.mangTheLoai = mangTheLoai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_the_loai_theo_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = mangTheLoai.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgHinhNen);
        holder.txtTenTheLoai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return mangTheLoai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgHinhNen;
        TextView txtTenTheLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinhNen = itemView.findViewById(R.id.imageviewtheloaitheochude);
            txtTenTheLoai = itemView.findViewById(R.id.textviewtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idtheloai",mangTheLoai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
