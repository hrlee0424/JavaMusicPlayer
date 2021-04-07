package hyerim.my.musicplayer.dto;

import androidx.annotation.NonNull;

public class Song {
    private final String mTitle;
    private final int mTrackNumber;
    private final int mDuration;
    private final String mPath;
    private final String mAlbumName;
    private final int mArtistId;
    private final String mArtistName;
    private final int mYear;

    public Song(@NonNull final String title, final int trackNumber, final int year, final int duration, final String path, final String albumName, final int artistId, final String artistName){
        this.mTitle = title;
        this.mTrackNumber = trackNumber;
        this.mYear = year;
        this.mDuration = duration;
        this.mPath = path;
        this.mAlbumName = albumName;
        this.mArtistId = artistId;
        this.mArtistName = artistName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public int getmTrackNumber() {
        return mTrackNumber;
    }

    public int getmDuration() {
        return mDuration;
    }

    public String getmPath() {
        return mPath;
    }

    public String getmAlbumName() {
        return mAlbumName;
    }

    public int getmArtistId() {
        return mArtistId;
    }

    public String getmArtistName() {
        return mArtistName;
    }

    public int getmYear() {
        return mYear;
    }
}
