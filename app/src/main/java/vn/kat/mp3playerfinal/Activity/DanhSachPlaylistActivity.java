package vn.kat.mp3playerfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.kat.mp3playerfinal.Adapter.DanhSachPlaylistAdapter;
import vn.kat.mp3playerfinal.Model.Playlist;
import vn.kat.mp3playerfinal.R;
import vn.kat.mp3playerfinal.Service.APIService;
import vn.kat.mp3playerfinal.Service.DataService;

public class DanhSachPlaylistActivity extends AppCompatActivity {

    Toolbar toolbarDanhSachCacPlaylist;
    RecyclerView rcvDanhSachPlaylist;
    DanhSachPlaylistAdapter danhSachPlaylistAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_playlist);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetDanhSachPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
                danhSachPlaylistAdapter = new DanhSachPlaylistAdapter(DanhSachPlaylistActivity.this, playlistArrayList);
                rcvDanhSachPlaylist.setLayoutManager(new GridLayoutManager(DanhSachPlaylistActivity.this, 2));
                rcvDanhSachPlaylist.setAdapter(danhSachPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarDanhSachCacPlaylist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbarDanhSachCacPlaylist.setTitleTextColor(getResources().getColor(R.color.mauTim));
        toolbarDanhSachCacPlaylist.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbarDanhSachCacPlaylist = findViewById(R.id.toolbarDanhSachCacPlaylist);
        rcvDanhSachPlaylist = findViewById(R.id.rcvDanhSachPlaylist);
    }
}
