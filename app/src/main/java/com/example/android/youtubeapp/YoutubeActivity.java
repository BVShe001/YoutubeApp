package com.example.android.youtubeapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class YoutubeActivity extends AppCompatActivity {

    private PlaylistViewModel playlistViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
        setupbindings(savedInstanceState);
    }

    private void setupbindings(Bundle savedInstanceState) {
        ActivityYoutubeBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_youtube);
        playlistViewModel = ViewModelProviders.of(this).get(PlaylistViewModel.class);
        playlistViewModel.getPlaylists().observe(this, new Observer<List<YoutubePlaylist>>() {
            @Override
            public void onChanged(@Nullable List<YoutubePlaylist> youtubePlaylists) {
                playlistViewModel.loading.set(View.GONE);
                if(youtubePlaylists.size()==0){
                    playlistViewModel.showEmpty.set(View.VISIBLE);
                }else{
                    playlistViewModel.showEmpty.set(View.GONE);
                    playlistViewModel.setPlaylistsInAdapter(youtubePlaylists);
                }

            }
        });
        setUpListClick();
    }

    private void setUpListClick() {
        playlistViewModel.getSelected().observe(this, new Observer<YoutubePlaylist>() {
            @Override
            public void onChanged(@Nullable YoutubePlaylist youtubePlaylist) {
                if(youtubePlaylist != null){
                    Toast.makeText(YoutubeActivity.this, "you selected a "+youtubePlaylist.getPlaylist_id(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
