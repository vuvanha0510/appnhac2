package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;

import com.example.appnhac.Adapter.DanhSachBaiHatAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.Model.PlayList;
import com.example.appnhac.Model.QuangCao;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    QuangCao quangcao;
    ImageView imgDanhSachCaKhuc;

    ArrayList<BaiHat> mangBaiHat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    PlayList playList;
    TheLoai theLoai;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        DataIntent();
        anhxa();
        init();

        if( quangcao != null && !quangcao.getTenBaiHat().equals("")){
            setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            getDataQuangCao(quangcao.getIdQuangCao());
        }
        if(playList != null && !playList.getTen().equals("")){
            setValueInView(playList.getTen(), playList.getHinhPlaylist());
            getDataPlayList(playList.getIdPlaylist());
        }
        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIdTheLoai());
        }
        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhanhAlbum());
            getDataAlbum(album.getIdAlbum());
        }


    }

    private void getDataAlbum(String idAlbum) {
        Dataservice dataService = APIService.getService();
        Call<List<BaiHat>> callBack = dataService.getBaiHatTheoAlbum(idAlbum);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangBaiHat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void getDataTheLoai(String idTheLoai){
        Dataservice dataService = APIService.getService();
        Call<List<BaiHat>> callBack = dataService.getDanhSachBaiHatTheoTheLoai(idTheLoai);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangBaiHat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void getDataQuangCao(String idQuangCao) {
        Dataservice dataService = APIService.getService();
        Call<List<BaiHat>> callBack = dataService.getDanhSachBaiHatTheoQuangCao(idQuangCao);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangBaiHat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlayList(String idPlayList) {
        Dataservice dataService = APIService.getService();
        Call<List<BaiHat>> callBack = dataService.getDanhSachBaiHatTheoPlayList(idPlayList);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangBaiHat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgDanhSachCaKhuc);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recycleviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgDanhSachCaKhuc = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangcao = (QuangCao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }

    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this,PlayNhacActivity.class);
                intent.putExtra("cacbaihat",mangBaiHat);
                startActivity(intent);
            }
        });
    }
}