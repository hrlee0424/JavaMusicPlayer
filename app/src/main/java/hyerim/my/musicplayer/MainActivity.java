package hyerim.my.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import hyerim.my.musicplayer.adapter.ViewPagerAdapter;
import hyerim.my.musicplayer.fragment.AlbumsFragment;
import hyerim.my.musicplayer.fragment.ArtistFragment;
import hyerim.my.musicplayer.fragment.FolderFragment;
import hyerim.my.musicplayer.fragment.PlayListFragment;
import hyerim.my.musicplayer.fragment.SongFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private Fragment playListFragment, songFragment, artistFragment, albumsFragment, folderFragment;
    private FragmentStateAdapter pagerAdapter;
    private ViewPager2 viewPager2;
    public BottomNavigationView bottomView;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MusicPlayer);
        setContentView(R.layout.activity_main);
        checkPermission();
//        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        pagerAdapter = new ViewPagerAdapter(this);
        bottomView = findViewById(R.id.bottomNavigationView);
        LinearLayout playLayout = findViewById(R.id.playLayout);
        viewPager2.setAdapter(pagerAdapter);

        bottomView.setOnNavigationItemSelectedListener(listener);
        playListFragment = new PlayListFragment();
        songFragment = new SongFragment();
        artistFragment = new ArtistFragment();
        albumsFragment = new AlbumsFragment();
        folderFragment = new FolderFragment();

        playLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
            startActivity(intent);
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                select(position);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }

    public void select(int position){
        switch (position){
            case 0:
                bottomView.setSelectedItemId(R.id.menu1); //bottomNavigationView 선택하기
                break;
            case 1:
                bottomView.setSelectedItemId(R.id.menu2); //bottomNavigationView 선택하기
                break;
            case 2:
                bottomView.setSelectedItemId(R.id.menu3); //bottomNavigationView 선택하기
                break;
            case 3:
                bottomView.setSelectedItemId(R.id.menu4); //bottomNavigationView 선택하기
                break;
            case 4:
                bottomView.setSelectedItemId(R.id.menu5); //bottomNavigationView 선택하기
                break;
        }
        bottomView.setSelected(true);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu1:
                    viewPager2.setCurrentItem(0);
                    return true;
                case R.id.menu2:
                    viewPager2.setCurrentItem(1);
                    return true;
                case R.id.menu3:
                    viewPager2.setCurrentItem(2);
                    return true;
                case R.id.menu4:
                    viewPager2.setCurrentItem(3);
                    return true;
                case R.id.menu5:
                    viewPager2.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    public void checkPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("알림")
                        .setMessage("저장소 권한이 필요합니다. 환경 설정에서 저장소 권한을 허가해주세요.\n 취소시 앱이 종료됩니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "앱 권한이 거부되어 종료되었습니다. 설정에서 허용해주세요.", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .create()
                        .show();
            }
        }
    }
}