package hyerim.my.musicplayer.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.R;
import hyerim.my.musicplayer.adapter.AlbumsAdapter;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AlbumsFragment extends Fragment {
    private static String TAG = AlbumsFragment.class.getSimpleName();

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
        };

        ContentResolver content = getActivity().getContentResolver();
        Cursor media_cursor = content.query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                projection,
                null,//Selection Statement
                null,//Selection Arguments replacement for ? in where id=?
                MediaStore.Audio.Albums.ALBUM + "");

        albums_recyclerView.setAdapter(new AlbumsAdapter(getContext(), media_cursor));

    }
}