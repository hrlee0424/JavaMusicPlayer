package hyerim.my.musicplayer.adapter;

import android.content.Context;
import android.database.Cursor;
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
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.R;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {
    private String TAG = AlbumsAdapter.class.getSimpleName();
    private Cursor cursor;
    private Context context;
    private LayoutInflater inflater;

    public AlbumsAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AlbumsAdapter.AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.albums_list_item, parent, false);
        return new AlbumsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            cursor.moveToFirst();
        }
        holder.textView.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST)));

        String album_art = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));

        Glide.with(context)
                .load(album_art)
                .placeholder(R.drawable.splash_icon)
                .error(R.drawable.ic_launcher_background)
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, textView.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
