package hyerim.my.musicplayer.dto;

public class Song {
    private Long _ID;
    private Long album_ID;
    private String title;
    private Long duration;
    private String albumArt;
    private String directory;
    private String artist;
    private String album;
    private String genre;
    private String year;
    private String track;
    private String displayName;

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

    public void setAlbum_ID(Long album_ID) {
        this.album_ID = album_ID;
    }

    public Long getAlbum_ID() {
        return album_ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public Long get_ID() {
        return _ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
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

    public void setDuration(Long duration) {
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

    public String getTitle() {
        return title;
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

    public Long getDuration() {
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
