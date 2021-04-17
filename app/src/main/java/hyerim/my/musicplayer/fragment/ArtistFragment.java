package hyerim.my.musicplayer.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.R;
import hyerim.my.musicplayer.adapter.ArtistAdapter;
import hyerim.my.musicplayer.dto.Artist;
import hyerim.my.musicplayer.dto.Song;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ArtistFragment extends Fragment {
    public ArrayList<Artist> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*RecyclerView artist_recyclerView = getActivity().findViewById(R.id.artist_recyclerView);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getContext());
        artist_recyclerView.setLayoutManager(layoutManager);

        String[] projection = {
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM_ART,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
        };

        ContentResolver content = getActivity().getContentResolver();
        Cursor media_cursor = content.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,//Selection Statement
                null,//Selection Arguments replacement for ? in where id=?
                MediaStore.Audio.Media.ARTIST);*/

        /*while (media_cursor.moveToNext()){
            Artist artist = new Artist();
//            artist.setTitle(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
//            artist.setAlbum_ID(media_cursor.getLong(media_cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
//            artist.setAlbum(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
//            song.setDirectory(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.)));
            artist.set_ID(media_cursor.getLong(media_cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            artist.setArtist(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
//            artist.setDuration(media_cursor.getLong(media_cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
            list.add(song);
        }*/

//        artist_recyclerView.setAdapter(new ArtistAdapter(getContext(), artist_cursor));

    }
}