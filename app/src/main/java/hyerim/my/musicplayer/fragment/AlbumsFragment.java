package hyerim.my.musicplayer.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.R;
import hyerim.my.musicplayer.RecyclerViewDecoration;
import hyerim.my.musicplayer.adapter.AlbumsAdapter;
import hyerim.my.musicplayer.adapter.SongAdapter;
import hyerim.my.musicplayer.dto.Album;
import hyerim.my.musicplayer.dto.Song;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AlbumsFragment extends Fragment {
    private static String TAG = AlbumsFragment.class.getSimpleName();
    private final ArrayList<Album> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView albums_recyclerView = getActivity().findViewById(R.id.albums_recyclerView);
//        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager =new GridLayoutManager(getContext(),2);

        albums_recyclerView.setLayoutManager(layoutManager);

        String[] projection = {
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM_ART,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS_FOR_ARTIST,
        };

        ContentResolver content = getActivity().getContentResolver();
        Cursor media_cursor = content.query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                projection,
                null,//Selection Statement
                null,//Selection Arguments replacement for ? in where id=?
                MediaStore.Audio.Albums.ALBUM + "");

        while (media_cursor.moveToNext()){
            Album album = new Album();
            album.setAlbum(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM)));
            album.setAlbumArt(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART)));
            album.set_ID(media_cursor.getLong(media_cursor.getColumnIndex(MediaStore.Audio.Albums._ID)));
            album.setArtist(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST)));
            album.setSongs(media_cursor.getInt(media_cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS_FOR_ARTIST)));
            list.add(album);
        }

        media_cursor.close();

//        albums_recyclerView.setAdapter(new AlbumsAdapter(getContext(), media_cursor));
        albums_recyclerView.setAdapter(new AlbumsAdapter(getContext(), list));

     /*   albums_recyclerView.addItemDecoration(new RecyclerViewDecoration(10));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getContext(),new LinearLayoutManager(getContext()).getOrientation());
        albums_recyclerView.addItemDecoration(dividerItemDecoration);*/

    }
}