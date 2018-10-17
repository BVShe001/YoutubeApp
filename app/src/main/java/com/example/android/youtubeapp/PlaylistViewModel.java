package com.example.android.youtubeapp;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableInt;
import android.view.View;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import java.util.List;

public class PlaylistViewModel extends ViewModel {
    MutableLiveData<YoutubePlaylist> selected;
    PlaylistRepository playlistRepository;
    PlaylistAdapter playlistAdapter;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    GoogleAccountCredential googleAccountCredential;

    public void init(){
        googleAccountCredential = null;
        playlistRepository = new PlaylistRepository(googleAccountCredential);
        selected = new MutableLiveData<>();
        playlistAdapter = new PlaylistAdapter(R.layout.view_playlist,this);
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }


    public void fetchList() {
        playlistRepository.getDataFromAPI();
    }
    public void onItemClick(Integer index) {
        selected.setValue(getYoutubePlaylistAt(index));
    }

    public MutableLiveData<List<YoutubePlaylist>> getPlaylists(){
        return playlistRepository.getPlaylists();
    }

    public YoutubePlaylist getYoutubePlaylistAt(Integer index) {
        if (playlistRepository.getPlaylists().getValue() != null &&
                index != null &&
                playlistRepository.getPlaylists().getValue().size() > index) {
            return playlistRepository.getPlaylists().getValue().get(index);
        }
        return null;
    }
    public PlaylistAdapter getAdapter(){
        return playlistAdapter;
    }

    public void setPlaylistsInAdapter(List<YoutubePlaylist> playlists){
        this.playlistAdapter.setPlaylists(playlists);
        this.playlistAdapter.notifyDataSetChanged();
    }
    public MutableLiveData<YoutubePlaylist> getSelected(){
        return this.selected;
    }

    public PlaylistViewModel(){

    }

}
