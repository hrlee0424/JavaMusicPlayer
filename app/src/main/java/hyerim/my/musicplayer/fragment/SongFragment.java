package hyerim.my.musicplayer.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.R;
import hyerim.my.musicplayer.RecyclerViewDecoration;
import hyerim.my.musicplayer.adapter.SongAdapter;
import hyerim.my.musicplayer.dto.Song;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SongFragment extends Fragment {
    public ArrayList<Song> songList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView song_recyclerView = getActivity().findViewById(R.id.song_recyclerView);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getContext());
        song_recyclerView.setLayoutManager(layoutManager);

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID
        };

        ContentResolver content = getActivity().getContentResolver();
        Cursor media_cursor = content.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,//Selection Statement
                null,//Selection Arguments replacement for ? in where id=?
                MediaStore.Audio.Media.TITLE);

        /*while (media_cursor.moveToNext()){
            Song song = new Song();
            song.setName(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            song.setName(media_cursor.getString(media_cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            songList.add(song);
        }

        media_cursor.close();*/

//        song_recyclerView.setAdapter(new SongAdapter(getContext(), media_cursor));
        song_recyclerView.setAdapter(new SongAdapter(getContext(), media_cursor));

        song_recyclerView.addItemDecoration(new RecyclerViewDecoration(10));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getContext(),new LinearLayoutManager(getContext()).getOrientation());
        song_recyclerView.addItemDecoration(dividerItemDecoration);

    }
}