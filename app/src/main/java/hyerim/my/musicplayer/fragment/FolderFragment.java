package hyerim.my.musicplayer.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import hyerim.my.musicplayer.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FolderFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_folder, container, false);
    }
}