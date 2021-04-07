package hyerim.my.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import hyerim.my.musicplayer.fragment.AlbumsFragment;
import hyerim.my.musicplayer.fragment.ArtistFragment;
import hyerim.my.musicplayer.fragment.FolderFragment;
import hyerim.my.musicplayer.fragment.PlayListFragment;
import hyerim.my.musicplayer.fragment.SongFragment;

import android.Manifest;
import android.app.AlertDialog;
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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
private Fragment playListFragment, songFragment, artistFragment, albumsFragment, folderFragment;

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
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        BottomNavigationView bottomView = findViewById(R.id.bottomNavigationView);

        bottomView.setOnNavigationItemSelectedListener(listener);
        playListFragment = new PlayListFragment();
        songFragment = new SongFragment();
        artistFragment = new ArtistFragment();
        albumsFragment = new AlbumsFragment();
        folderFragment = new FolderFragment();

        selectEvent(playListFragment);
    }

    private void selectEvent(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu1:
                    selectEvent(playListFragment);
                    return true;
                case R.id.menu2:
                    selectEvent(songFragment);
                    return true;
                case R.id.menu3:
                    selectEvent(artistFragment);
                    return true;
                case R.id.menu4:
                    selectEvent(albumsFragment);
                    return true;
                case R.id.menu5:
                    selectEvent(folderFragment);
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