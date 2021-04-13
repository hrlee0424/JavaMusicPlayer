package hyerim.my.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import hyerim.my.musicplayer.databinding.ActivityMainBinding;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String TAG = PlayerActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_player);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player);
//        binding.setMainActivity();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String title = bundle.getString("title");
            String artist = bundle.getString("artist");
            long albumId = bundle.getLong("albumId");
            long _id = bundle.getLong("_id");
            long duration = bundle.getLong("duration");
            String album = bundle.getString("album");

            Uri musicURI = Uri.withAppendedPath(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, ""+ _id);

            try {
                mediaPlayer.setDataSource(this, musicURI);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "onCreate: " + title + " " + artist + " " + albumId + " " + album + " duration: " + duration + " _id " + _id + " musicURI : " + musicURI);
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                mediaPlayer.start();
                Log.i(TAG, "onPrepared: " + "start111111111111");
            }
        });


    }
}