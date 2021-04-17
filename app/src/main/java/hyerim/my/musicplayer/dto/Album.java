package hyerim.my.musicplayer.dto;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private Long _ID;
    private Long album_ID;
    private Long duration;
    private String album;
    private String artist;
    private String albumArt;
    private int songs;

    public void setSongs(int songs) {
        this.songs = songs;
    }

    public int getSongs() {
        return songs;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public void setAlbum_ID(Long album_ID) {
        this.album_ID = album_ID;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Long get_ID() {
        return _ID;
    }

    public Long getAlbum_ID() {
        return album_ID;
    }

    public Long getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    /*private List<Song> songs;
    private int position;

    public Album() {
        songs = new ArrayList<>();
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public int getPosition() {
        return position;
    }*/
}
