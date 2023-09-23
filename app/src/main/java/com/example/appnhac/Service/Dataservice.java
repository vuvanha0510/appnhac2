package com.example.appnhac.Service;

import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.ChuDeTheLoaiTrongNgay;
import com.example.appnhac.Model.PlayList;
import com.example.appnhac.Model.QuangCao;
import com.example.appnhac.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> getPlayListCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeTheLoaiTrongNgay> getCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> getAlbumHot();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> getBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idQuangCao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idPlayList);

    @GET("danhsachcacplaylist.php")
    Call<List<PlayList>> getDanhSachPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idTheLoai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> getAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> getTheLoaiTheoChuDe(@Field("idchude") String idChuDe);

    @GET("tatcaalbum.php")
    Call<List<Album>> getAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getBaiHatTheoAlbum(@Field("idalbum") String idAlbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> getSearchBaiHat(@Field("tukhoa") String tuKhoa);

}
