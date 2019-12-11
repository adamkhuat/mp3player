package vn.kat.mp3playerfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.kat.mp3playerfinal.Model.BaiHat;
import vn.kat.mp3playerfinal.R;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.tvTenBaiHat.setText(baiHat.getTenBaiHat());
        holder.tvCaSi.setText(baiHat.getCaSi());
        holder.tvIndex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndex, tvTenBaiHat, tvCaSi;
        ImageView imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCaSi = itemView.findViewById(R.id.tvDanhSachTenCaSi);
            tvIndex = itemView.findViewById(R.id.tvDanhSachIndex);
            tvTenBaiHat = itemView.findViewById(R.id.tvDanhSachTenBaiHat);
            imgLuotThich = itemView.findViewById(R.id.imgLuotThichInDanhSach);
        }
    }
}
