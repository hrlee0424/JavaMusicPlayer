package hyerim.my.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
private Fragment playListFragment, songFragment, artistFragment, albumsFragment, folderFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewPager2 main_vp = findViewById(R.id.main_vp);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        BottomNavigationView bottomView = findViewById(R.id.bottomNavigationView);

        bottomView.setOnNavigationItemSelectedListener(listener);
        playListFragment = new PlayListFragment();
        songFragment = new SongFragment();
        artistFragment = new ArtistFragment();
        albumsFragment = new AlbumsFragment();
        folderFragment = new FolderFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, playListFragment).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, playListFragment).commit();
                    return true;
                case R.id.menu2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, songFragment).commit();
                    return true;
                case R.id.menu3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, artistFragment).commit();
                    return true;
                case R.id.menu4:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, albumsFragment).commit();
                    return true;
                case R.id.menu5:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, folderFragment).commit();
                    return true;
            }
            return false;
        }
    };
}