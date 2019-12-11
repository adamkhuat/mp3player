package vn.kat.mp3playerfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.kat.mp3playerfinal.Adapter.DanhSachChuDeAdapter;
import vn.kat.mp3playerfinal.Model.ChuDe;
import vn.kat.mp3playerfinal.R;
import vn.kat.mp3playerfinal.Service.APIService;
import vn.kat.mp3playerfinal.Service.DataService;

public class DanhSachChuDeActivity extends AppCompatActivity {

    Toolbar toolbarChuDe;
    RecyclerView rcvChuDe;
    DanhSachChuDeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.GetDanhSachChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter  =new DanhSachChuDeAdapter(DanhSachChuDeActivity.this, chuDeArrayList);
                rcvChuDe.setLayoutManager(new GridLayoutManager(DanhSachChuDeActivity.this, 1));
                rcvChuDe.setAdapter(danhSachChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbarChuDe = findViewById(R.id.toolbarChuDe);
        rcvChuDe = findViewById(R.id.rcvChuDe);
    }
}
