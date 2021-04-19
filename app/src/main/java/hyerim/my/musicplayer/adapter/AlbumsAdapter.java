package hyerim.my.musicplayer.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import hyerim.my.musicplayer.AlbumActivity;
import hyerim.my.musicplayer.R;
import hyerim.my.musicplayer.dto.Album;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {
    private String TAG = AlbumsAdapter.class.getSimpleName();
    private Cursor cursor;
    private Context context;
    private List<Album> albumList;
    Bundle bundle = new Bundle();

    /*public AlbumsAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }*/

    public AlbumsAdapter(Context context, List<Album> albumList){
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumsAdapter.AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View v = inflater.inflate(R.layout.albums_list_item, parent, false);
        return new AlbumsViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsViewHolder holder, int position) {
        /*if (!cursor.moveToPosition(position)) {
            cursor.moveToFirst();
        }*/

//        final Album album = albumList.get(holder.getAdapterPosition());


//        albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Albums._ID));
        if (albumList.size() != 0) {
            holder.albumId = albumList.get(position).get_ID();

            Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
            Uri sAlbumArtUri = ContentUris.withAppendedId(sArtworkUri, holder.albumId);

//        holder.textView.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM)));
            holder.textView.setText(albumList.get(position).getAlbum());
            holder.albums_num_song.setText("곡 " + albumList.get(position).getSongs() + "개");
            holder.art = albumList.get(position).getAlbumArt();
            Log.i(TAG, "onBindViewHolder: art" + holder.art);

            Glide.with(context)
                .load(sAlbumArtUri)
//                .placeholder(R.drawable.splash_icon)
                    .error(R.drawable.ic_baseline_album_24)
                    .fallback(R.drawable.ic_baseline_album_24)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
//        return cursor.getCount();
        return albumList.size();
    }

    class AlbumsViewHolder extends RecyclerView.ViewHolder{
        TextView textView, albums_num_song ;
        ImageView imageView;
        long id;
        long albumId;
        String art;

        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.albums_item_text);
            albums_num_song = itemView.findViewById(R.id.albums_num_song);
            imageView = itemView.findViewById(R.id.albums_item_img);
            imageView.setClipToOutline(true);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), AlbumActivity.class);
                bundle.putLong("albumId", albumId);
                bundle.putString("artist", textView.getText().toString());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            });
        }
    }
}
