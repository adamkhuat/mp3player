package vn.kat.mp3playerfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.kat.mp3playerfinal.Model.BaiHat;
import vn.kat.mp3playerfinal.R;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public PlayMusicAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.tvPlayMusicTenCaSi.setText(baiHat.getCaSi());
        holder.tvPlayMusicIndex.setText(position + 1 + "");
        holder.tvPlayMusicTenBaiHat.setText(baiHat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlayMusicIndex;
        TextView tvPlayMusicTenBaiHat;
        TextView tvPlayMusicTenCaSi;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlayMusicIndex = itemView.findViewById(R.id.tvPlayMusicIndex);
            tvPlayMusicTenBaiHat = itemView.findViewById(R.id.tvPlayMusicTenBaiHat);
            tvPlayMusicTenCaSi = itemView.findViewById(R.id.tvPlayMusicTenCaSi);
        }
    }

}
