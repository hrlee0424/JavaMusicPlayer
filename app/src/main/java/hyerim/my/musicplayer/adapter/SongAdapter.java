package hyerim.my.musicplayer.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.PlayerActivity;
import hyerim.my.musicplayer.R;
import hyerim.my.musicplayer.dto.Song;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private String TAG = SongAdapter.class.getSimpleName();
//    private final Cursor cursor;
    private final Context context;
    public List<Song> songList;
    Bundle bundle = new Bundle();

    /*public SongAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }*/

    public SongAdapter(Context context, List songList){
        this.context = context;
        this.songList = songList;
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
        /*if ( !cursor.moveToPosition(position) ) {
            cursor.moveToFirst();
        }

        long albumId = cursor.getLong(cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ALBUM_ID));
        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION));
        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
        Uri sAlbumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);

        Log.i(TAG, "onBindViewHolder: " + title + " " + artist + " " + album + " " + cursor.getPosition());
        holder.song_list_text.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        holder.song_list_artist.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
        holder.song_list_duration.setText(DateFormat.format("mm:ss", duration));
*/

        if (!songList.isEmpty()){
            holder.duration = songList.get(position).getDuration();
            holder.albumId = songList.get(position).getAlbum_ID();
            Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
            Uri sAlbumArtUri = ContentUris.withAppendedId(sArtworkUri, holder.albumId);
            holder.title = songList.get(position).getTitle();
            holder.artist = songList.get(position).getArtist();
            holder.album = songList.get(position).getAlbum();
            holder._id = songList.get(position).get_ID();

            holder.song_list_text.setText(holder.title);
            holder.song_list_duration.setText(DateFormat.format("mm:ss", holder.duration));
            holder.song_list_artist.setText(holder.artist);

            Glide.with(context)
                    .load(sAlbumArtUri)
//                .placeholder(R.drawable.splash_icon)
                    .error(R.drawable.ic_baseline_album_24)
                    .fallback(R.drawable.ic_baseline_album_24)
                    .into(holder.song_list_img);
        }

        /*Glide.with(context)
                .load(sAlbumArtUri)
//                .placeholder(R.drawable.splash_icon)
                .error(R.drawable.ic_baseline_album_24)
                .fallback(R.drawable.ic_baseline_album_24)
                .into(holder.song_list_img);*/

//        album_art = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ARTIST));
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }

    @Override
    public int getItemCount() {
//        return cursor.getCount();
        return songList.size();

    }


    class SongViewHolder extends RecyclerView.ViewHolder{
        TextView song_list_text, song_list_artist, song_list_duration;
        ImageView song_list_img;
        long duration;
        Long albumId, _id;
        String title, artist, album;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            song_list_text = itemView.findViewById(R.id.song_list_text);
            song_list_artist = itemView.findViewById(R.id.song_list_artist);
            song_list_img = itemView.findViewById(R.id.song_list_img);
            song_list_duration = itemView.findViewById(R.id.song_list_duration);
            song_list_img.setClipToOutline(true); //?????? ????????? ??????

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    bundle.putLong("albumId", albumId);
                    bundle.putString("title", title);
                    bundle.putString("artist", artist);
                    bundle.putString("album", album);
                    bundle.putLong("duration", duration);
                    bundle.putLong("_id", _id);

//                    bundle.putString(MediaStore.Audio.Media.DURATION, +min+":"+(seconds%60));
//                    bundle.putString("album_art", album_art);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
