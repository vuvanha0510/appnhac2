package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appnhac.Adapter.DanhSachTheLoaiTheoChuDeAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewTheLoaiTheoChuDe;
    Toolbar toolbarTheLoaiTheoChuDe;
    DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);

        GetIntent();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataService = APIService.getService();
        Call<List<TheLoai>> callBack = dataService.getTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callBack.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangTheLoai = (ArrayList<TheLoai>) response.body();
                danhSachTheLoaiTheoChuDeAdapter = new DanhSachTheLoaiTheoChuDeAdapter(DanhSachTheLoaiTheoChuDeActivity.this, mangTheLoai);
                recyclerViewTheLoaiTheoChuDe.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this, 2));
                recyclerViewTheLoaiTheoChuDe.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTheLoaiTheoChuDe = findViewById(R.id.recycleviewtheloaitheochude);
        toolbarTheLoaiTheoChuDe = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbarTheLoaiTheoChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarTheLoaiTheoChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")) {
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this, chuDe.getTenChuDe(), Toast.LENGTH_SHORT).show();
        }
    }
}