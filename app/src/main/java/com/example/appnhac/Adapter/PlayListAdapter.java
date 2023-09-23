package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appnhac.Model.PlayList;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {
    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView txtTenPlayList;
        ImageView imgBackGround, imgPlayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenPlayList = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgPlayList = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgBackGround = convertView.findViewById(R.id.imagebackgroundplaylist);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhPlaylist()).into(viewHolder.imgBackGround);
        Picasso.with(getContext()).load(playList.getIcon()).into(viewHolder.imgPlayList);
        viewHolder.txtTenPlayList.setText(playList.getTen());
        return convertView;
    }
}
