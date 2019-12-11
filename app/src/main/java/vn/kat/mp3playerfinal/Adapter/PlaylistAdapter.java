package vn.kat.mp3playerfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.kat.mp3playerfinal.Model.Playlist;
import vn.kat.mp3playerfinal.R;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView tvTenPlaylist;
        ImageView imgBackgroundPlaylist, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTenPlaylist = convertView.findViewById(R.id.tvTenPlaylist);
            viewHolder.imgBackgroundPlaylist = convertView.findViewById(R.id.imgBackgroundPlaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imgPlaylist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhAnh()).into(viewHolder.imgBackgroundPlaylist);
        Picasso.with(getContext()).load(playlist.getHinhIcon()).into(viewHolder.imgPlaylist);
        viewHolder.tvTenPlaylist.setText(playlist.getTen());
        return convertView;
    }
}
