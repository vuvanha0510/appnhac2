package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appnhac.Adapter.DanhSachChuDeAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChuDeActivity extends AppCompatActivity {

    RecyclerView recyclerViewChuDe;
    Toolbar toolbarChuDe;
    DanhSachChuDeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);

        init();
        getData();
    }

    private void getData() {
        Dataservice dataService = APIService.getService();
        Call<List<ChuDe>> callBack = dataService.getAllChuDe();
        callBack.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangChuDe = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter = new DanhSachChuDeAdapter(DanhSachChuDeActivity.this,mangChuDe);
                recyclerViewChuDe.setLayoutManager(new GridLayoutManager(DanhSachChuDeActivity.this,1));
                recyclerViewChuDe.setAdapter(danhSachChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewChuDe = findViewById(R.id.recycleviewchude);
        toolbarChuDe = findViewById(R.id.toolbarchude);
        setSupportActionBar(toolbarChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}