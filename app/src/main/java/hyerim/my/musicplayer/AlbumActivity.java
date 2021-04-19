package hyerim.my.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.adapter.SongAdapter;
import hyerim.my.musicplayer.dto.Song;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {
    private String TAG = AlbumActivity.class.getSimpleName();
    private ArrayList<Song> songs = new ArrayList<>();
    public long albumId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        RecyclerView album_recyclerView = findViewById(R.id.album_recyclerView);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getApplicationContext());
        album_recyclerView.setLayoutManager(layoutManager);

        Toolbar toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        toolBar.setTitle("HELLO EXAMPLE");
//        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(false); //툴바에 백키(<-) 보이게할거면 이거
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            albumId = bundle.getLong("albumId");
            String artist = bundle.getString("artist");
            Log.i(TAG, "onCreate: " + albumId + " "+ artist);
        }

//        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String selection = MediaStore.Audio.Albums.ALBUM_ID + " = ?";

        String[] selectionArgs = new String[] {
                String.valueOf(albumId)};

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID
        };

        ContentResolver content = getContentResolver();
        Cursor media_cursor = content.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,//Selection Statement
                selectionArgs,//Selection Arguments replacement for ? in where id=?
                MediaStore.Audio.Media.TITLE);


        while (media_cursor.moveToNext()){
            Song song = new Song();
            song.setTitle(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            song.setAlbum_ID(media_cursor.getLong(media_cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
            song.setAlbum(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
//            song.setDirectory(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.)));
            song.set_ID(media_cursor.getLong(media_cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            song.setArtist(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            song.setDuration(media_cursor.getLong(media_cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
//            String a = media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            songs.add(song);
            Log.i(TAG, "onCreate: getTitle" + song.getTitle());
        }
        media_cursor.close();

        album_recyclerView.setAdapter(new SongAdapter(getApplicationContext(), songs));
    }
}