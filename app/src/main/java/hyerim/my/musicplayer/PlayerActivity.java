package hyerim.my.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

public class PlayerActivity extends AppCompatActivity {
    private MediaPlayer musicPlayer = new MediaPlayer();
    private String TAG = PlayerActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String title = bundle.getString(MediaStore.Audio.Media.TITLE);
//        String artist = bundle.getString("artist");
            Log.i(TAG, "onCreate: " + title);
        }

    }
}