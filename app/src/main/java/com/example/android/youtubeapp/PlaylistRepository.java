package com.example.android.youtubeapp;

import android.arch.lifecycle.MutableLiveData;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import java.util.List;

public class PlaylistRepository {
    List<YoutubePlaylist> youtubePlaylists;
    GoogleAccountCredential googleAccountCredential;
    YoutubeApi youtubeApi;
    MutableLiveData<List<YoutubePlaylist>> playlists = new MutableLiveData<>();

    public List<YoutubePlaylist> getYoutubePlaylists() {
        return youtubePlaylists;
    }

    public MutableLiveData<List<YoutubePlaylist>> getPlaylists(){
        return this.playlists;
    }

    public PlaylistRepository(GoogleAccountCredential googleAccountCredential) {
        getDataFromAPI();
        this.googleAccountCredential = googleAccountCredential;
    }

    public void getDataFromAPI() {
        if (googleAccountCredential != null) {
            youtubeApi = new YoutubeApi(googleAccountCredential);
            this.youtubePlaylists = youtubeApi.getPlaylists();
            }
    }
}
