package vn.kat.mp3playerfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.kat.mp3playerfinal.Adapter.TatCaAlbumAdapter;
import vn.kat.mp3playerfinal.Model.Album;
import vn.kat.mp3playerfinal.R;
import vn.kat.mp3playerfinal.Service.APIService;
import vn.kat.mp3playerfinal.Service.DataService;

public class DanhSachTatCaAlbumActivity extends AppCompatActivity {

    Toolbar toolbarTatCaAlbum;
    RecyclerView rcvTatCaAlbum;
    TatCaAlbumAdapter tatCaAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_album);
        init();
        GetData();

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                Log.d("AAAAAAAAAa", albumArrayList.get(0).getTenAlbum());
                tatCaAlbumAdapter = new TatCaAlbumAdapter(DanhSachTatCaAlbumActivity.this, albumArrayList);
                rcvTatCaAlbum.setLayoutManager(new GridLayoutManager(DanhSachTatCaAlbumActivity.this, 2));
                rcvTatCaAlbum.setAdapter(tatCaAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbarTatCaAlbum = findViewById(R.id.toolbarTatCaAlbum);
        rcvTatCaAlbum = findViewById(R.id.rcvTatCaAlbum);
        setSupportActionBar(toolbarTatCaAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbarTatCaAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
