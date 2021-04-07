package hyerim.my.musicplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.PlayerActivity;
import hyerim.my.musicplayer.R;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private String TAG = SongAdapter.class.getSimpleName();
    private final Cursor cursor;
    private final Context context;
    private String title, artist, album, album_art;
    Bundle bundle = new Bundle();

    public SongAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View v = inflater.inflate(R.layout.song_list_item, parent, false);
        return new SongViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        if ( !cursor.moveToPosition(position) ) {
//            cursor.moveToFirst();
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }


        Log.i(TAG, "onBindViewHolder: " + title + " " + artist + " " + album + " " + cursor.getPosition());
        holder.song_list_text.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));

//        album_art = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ARTIST));
        holder.song_list_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                Intent intent = new Intent(v.getContext(), PlayerActivity.class);
//                    bundle.putLong(MediaStore.Audio.Media._ID, cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                bundle.putString("title", title);
                bundle.putString("artist", artist);
                bundle.putString("album", album);
//                    bundle.putString(MediaStore.Audio.Media.DURATION, +min+":"+(seconds%60));
//                bundle.putString("album_art", album_art);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    class SongViewHolder extends RecyclerView.ViewHolder{
        TextView song_list_text;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            song_list_text = itemView.findViewById(R.id.song_list_text);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, song_list_text.getText().toString(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    bundle.putLong(MediaStore.Audio.Media._ID, cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                    bundle.putString(MediaStore.Audio.Media.TITLE, cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                    bundle.putString(MediaStore.Audio.Media.ARTIST, artist);
                    bundle.putString(MediaStore.Audio.Media.ALBUM, album);
//                    bundle.putString(MediaStore.Audio.Media.DURATION, +min+":"+(seconds%60));
//                    bundle.putString("album_art", album_art);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });*/
        }
    }
}