package hyerim.my.musicplayer.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.musicplayer.R;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private final Cursor cursor;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public SongAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.song_list_item, parent, false);
        return new SongViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        if ( !cursor.moveToPosition(position) ) {
            cursor.moveToFirst();
        }
        holder.song_list_text.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
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

        }
    }
}
