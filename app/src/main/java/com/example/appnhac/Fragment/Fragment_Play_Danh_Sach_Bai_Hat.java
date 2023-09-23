package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.PlayNhacActivity;
import com.example.appnhac.Adapter.PlayNhacAdapter;
import com.example.appnhac.R;

public class Fragment_Play_Danh_Sach_Bai_Hat extends Fragment {

    View view;
    RecyclerView recyclerViewPlayNhac;
    PlayNhacAdapter playNhacAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_bai_hat,container,false);
        recyclerViewPlayNhac = view.findViewById(R.id.recycleviewplaybaihat);
        if(PlayNhacActivity.mangBaiHat.size() > 0) {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.mangBaiHat);
            recyclerViewPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayNhac.setAdapter(playNhacAdapter);
        }
        return view;
    }
}
