package hyerim.my.musicplayer.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.RemoteViews;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import hyerim.my.musicplayer.MainActivity;
import hyerim.my.musicplayer.PlayerActivity;
import hyerim.my.musicplayer.R;

public class MusicService extends Service {
    private String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mediaPlayer;
    private Uri uri;
    private String title, artist;
    private Context context;
    private long albumId, _id;
    private String CHANNEL_ID;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "onCreate: MusicService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.cancel(Integer.parseInt(CHANNEL_ID));
        notificationManager.cancelAll();
        Log.i(TAG, "onDestroy: MusicService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mediaPlayer = MediaPlayer.create(this,R.raw.test);  //mp3시작 및 반복
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();
//        return super.onStartCommand(intent, flags, startId);
        title = intent.getStringExtra("title");
        artist = intent.getStringExtra("artist");
        albumId = intent.getLongExtra("albumId", 0);
        _id = intent.getLongExtra("_id", 0);

        showNoti();
        Log.d("titleeeeeeeeee", title);
        return START_NOT_STICKY;
        //        Log.i(TAG, "onStartCommand: MusicService", "onStartCommand()");
    }

    void showNoti(){
        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
        Uri sAlbumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
        remoteViews.setImageViewResource(R.id.albumImg, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.title, title);
        remoteViews.setTextViewText(R.id.artist, artist);
        remoteViews.setImageViewUri(R.id.albumImg, sAlbumArtUri);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            remoteViews.setOnClickResponse(R.id.btn_back, pause());
        }
        Intent notificationIntent = new Intent(this, PlayerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            CHANNEL_ID = "service_channel";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "smart_channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(false);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE))
                    .createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        //uri to bitmap
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), sAlbumArtUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setLargeIcon(bitmap)
                .setContentIntent(pendingIntent);

        startForeground(1, builder.build());
    }


}

