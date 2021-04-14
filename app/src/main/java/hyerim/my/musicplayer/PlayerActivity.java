package hyerim.my.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import hyerim.my.musicplayer.databinding.PlayerActivityBinding;
import hyerim.my.musicplayer.service.MusicService;

import android.content.ContentUris;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String TAG = PlayerActivity.class.getSimpleName();
    private PlayerActivityBinding binding;
    private String title, artist, album;
    private long albumId, _id, duration;
    private boolean playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_player);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player);
        binding.setPlayerActivity(this);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.reset();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            title = bundle.getString("title");
            artist = bundle.getString("artist");
            albumId = bundle.getLong("albumId");
            _id = bundle.getLong("_id");
            duration = bundle.getLong("duration");
            album = bundle.getString("album");

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

        binding.title.setText(title);
        binding.artist.setText(artist);

        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
        Uri sAlbumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);

        Glide.with(getApplicationContext())
                .load(sAlbumArtUri)
//                .placeholder(R.drawable.splash_icon)
                .error(R.drawable.ic_baseline_album_24)
                .fallback(R.drawable.ic_baseline_album_24)
                .into(binding.artistImage);

        Drawable pauseImg = getResources().getDrawable(R.drawable.ic_pause1);
        Drawable playImg = getResources().getDrawable(R.drawable.ic_play_button);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                binding.playBtn.setImageDrawable(pauseImg);
                playing = true;
                Log.i(TAG, "onPrepared: " + "start111111111111");
            }
        });

        binding.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playing){
                    mediaPlayer.pause();
                    binding.playBtn.setImageDrawable(playImg);
                    playing = false;
                }else{
                    mediaPlayer.start();
                    binding.playBtn.setImageDrawable(pauseImg);
                    playing = true;
                }
            }
        });

    }
}