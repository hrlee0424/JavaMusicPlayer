package hyerim.my.musicplayer.dto;

import androidx.annotation.NonNull;

public class Song {
    private String directory;
    private String name;
    private String artist;
    private String album;
    private String genre;
    private String year;
    private String duration;
    private String track;
    private String displayName;
    private String albumArt;

    /*public Song(String directory, String name, String artist, String album,  String year, String duration, String track, String displayName,String albumArt) {
        this.directory = directory;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.duration = duration;
        this.track = track;
        this.displayName = displayName;
        this.albumArt=albumArt;
    }*/

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public String getDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public String getDuration() {
        return duration;
    }

    public String getTrack() {
        return track;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAlbumArt() {
        return albumArt;
    }
}
