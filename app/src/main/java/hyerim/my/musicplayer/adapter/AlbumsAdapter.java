package hyerim.my.musicplayer.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.R;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {
    private String TAG = AlbumsAdapter.class.getSimpleName();
    private Cursor cursor;
    private Context context;

    public AlbumsAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public AlbumsAdapter.AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View v = inflater.inflate(R.layout.albums_list_item, parent, false);
        return new AlbumsViewHolder(v);
    }

    long albumId;
    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            cursor.moveToFirst();
        }

        albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Albums._ID));

        Log.i(TAG, "onBindViewHolder: " + albumId);
        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
        Uri sAlbumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);

        holder.textView.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM)));

        Glide.with(context)
                .load(sAlbumArtUri)
//                .placeholder(R.drawable.splash_icon)
                .error(R.drawable.ic_baseline_album_24)
                .fallback(R.drawable.ic_baseline_album_24)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    class AlbumsViewHolder extends RecyclerView.ViewHolder{
        TextView textView ;
        ImageView imageView;
        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.albums_item_text);
            imageView = itemView.findViewById(R.id.albums_item_img);
            imageView.setClipToOutline(true);

            itemView.setOnClickListener(v -> {
                Toast.makeText(context, textView.getText().toString(),Toast.LENGTH_SHORT).show();
            });
        }
    }
}
