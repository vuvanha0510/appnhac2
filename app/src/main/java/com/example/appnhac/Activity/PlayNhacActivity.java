package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appnhac.Adapter.ViewPagerPlaylistNhac;
import com.example.appnhac.Fragment.Fragment_Dia_Nhac;
import com.example.appnhac.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import com.example.appnhac.Model.BaiHat;
import com.example.appnhac.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarPlayNhac;
    TextView txtTimeSong, txtTotaltimeSong;
    SeekBar seekTime;
    ImageButton imgPlay, imgNext, imgPreview, imgRepeat,imgRandom;
    ViewPager viewPagerPlayNhac;
    public static ArrayList<BaiHat> mangBaiHat = new ArrayList<>();
    public static ViewPagerPlaylistNhac adapterNhac;
    Fragment_Dia_Nhac fragmentDiaNhac;
    Fragment_Play_Danh_Sach_Bai_Hat fragmentPlayDanhSachBaiHat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getDataFromIntent();
        init();
        eventClick();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        mangBaiHat.clear();
        if(intent != null){
            if(intent.hasExtra("cakhuc")){
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                mangBaiHat.add(baiHat);
            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangBaiHat = baiHatArrayList;
            }
        }
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapterNhac.getItem(1) != null){
                    if(mangBaiHat.size() > 0){
                        fragmentDiaNhac.playNhac(mangBaiHat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                }else{
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat == false){
                    if(checkRandom == true){
                        checkRandom = false;
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                        imgRandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else{
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRandom == false){
                    if(repeat == true){
                        repeat = false;
                        imgRandom.setImageResource(R.drawable.iconshuffled);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgRandom.setImageResource(R.drawable.iconshuffled);
                    checkRandom = true;
                }else{
                    imgRandom.setImageResource(R.drawable.iconsuffle);
                    checkRandom = false;
                }
            }
        });
        seekTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangBaiHat.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer!= null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangBaiHat.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > (mangBaiHat.size()) - 1){
                            position = 0;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragmentDiaNhac.playNhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                        updateTime();
                    }
                }
                imgPreview.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPreview.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
            }
        });
        imgPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangBaiHat.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer!= null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangBaiHat.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if(position < 0){
                            position = mangBaiHat.size() - 1;
                        }
                        if(repeat == true){
                            position += 1;
                        }
                        if (checkRandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragmentDiaNhac.playNhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                        updateTime();
                    }
                }
                imgPreview.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPreview.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void init() {
        toolbarPlayNhac = findViewById(R.id.toolbarplaynhac);
        txtTimeSong = findViewById(R.id.textviewtimesong);
        txtTotaltimeSong = findViewById(R.id.textviewtotaltimesong);
        seekTime = findViewById(R.id.seekbarsong);
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgRepeat = findViewById(R.id.imagebuttonrepeat);
        imgNext = findViewById(R.id.imagebuttonnext);
        imgRandom = findViewById(R.id.imagebuttonsuffle);
        imgPreview = findViewById(R.id.imagebuttonpreview);
        viewPagerPlayNhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangBaiHat.clear();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
        fragmentDiaNhac = new Fragment_Dia_Nhac();
        fragmentPlayDanhSachBaiHat = new Fragment_Play_Danh_Sach_Bai_Hat();
        adapterNhac = new ViewPagerPlaylistNhac(getSupportFragmentManager());
        adapterNhac.addFragment(fragmentPlayDanhSachBaiHat);
        adapterNhac.addFragment(fragmentDiaNhac);
        viewPagerPlayNhac.setAdapter(adapterNhac);
        fragmentDiaNhac = (Fragment_Dia_Nhac) adapterNhac.getItem(1);
        if(mangBaiHat.size() > 0){
            getSupportActionBar().setTitle(mangBaiHat.get(0).getTenbaihat());
            new PlayMp3().execute(mangBaiHat.get(0).getLinkbaihat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }


    }

    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baiHat) {
            super.onPostExecute(baiHat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        mediaPlayer.stop();
                        mediaPlayer.reset();

                    }

                });
                mediaPlayer.setDataSource(baiHat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            updateTime();
        }
    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekTime.setMax(mediaPlayer.getDuration());
    }

    private void updateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if (position < (mangBaiHat.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > (mangBaiHat.size()) - 1){
                            position = 0;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragmentDiaNhac.playNhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                    }

                    imgPreview.setClickable(false);
                    imgNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgPreview.setClickable(true);
                            imgNext.setClickable(true);
                        }
                    },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}