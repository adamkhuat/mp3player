package vn.kat.mp3playerfinal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.kat.mp3playerfinal.Activity.PlayMusicActivity;
import vn.kat.mp3playerfinal.Model.BaiHat;
import vn.kat.mp3playerfinal.R;
import vn.kat.mp3playerfinal.Service.APIService;
import vn.kat.mp3playerfinal.Service.DataService;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);

        holder.tvSearchTenBaiHat.setText(baiHat.getTenBaiHat());
        holder.tvSearchTenCaSi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgSearchBaiHat);
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSearchBaiHat;
        TextView tvSearchTenBaiHat;
        TextView tvSearchTenCaSi;
        ImageView imgSearchLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSearchBaiHat = itemView.findViewById(R.id.imgSearchBaiHat);
            tvSearchTenBaiHat = itemView.findViewById(R.id.tvSearchTenBaiHat);
            tvSearchTenCaSi = itemView.findViewById(R.id.tvSearchTenCaSi);
            imgSearchLuotThich = itemView.findViewById(R.id.imgSearchLuotThich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("music", baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgSearchLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgSearchLuotThich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1", baiHatArrayList.get(getPosition()).getIDBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("OK")){
                                Toast.makeText(context, "Loved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgSearchLuotThich.setEnabled(false);
                }
            });

        }
    }
}
