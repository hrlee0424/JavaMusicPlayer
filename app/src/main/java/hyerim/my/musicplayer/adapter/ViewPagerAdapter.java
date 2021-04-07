package hyerim.my.musicplayer.adapter;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import hyerim.my.musicplayer.MainActivity;
import hyerim.my.musicplayer.PlayerActivity;
import hyerim.my.musicplayer.R;
import hyerim.my.musicplayer.fragment.AlbumsFragment;
import hyerim.my.musicplayer.fragment.ArtistFragment;
import hyerim.my.musicplayer.fragment.FolderFragment;
import hyerim.my.musicplayer.fragment.PlayListFragment;
import hyerim.my.musicplayer.fragment.SongFragment;

public class ViewPagerAdapter extends FragmentStateAdapter{

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new PlayListFragment();
        switch (position){
            case 0:
                fragment = new PlayListFragment();
                break;
            case 1:
                fragment = new SongFragment();
                break;
            case 2:
                fragment = new ArtistFragment();
                break;
            case 3:
                fragment = new AlbumsFragment();
                break;
            case 4:
                fragment = new FolderFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
