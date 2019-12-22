package vn.kat.mp3playerfinal.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.kat.mp3playerfinal.Adapter.SearchBaiHatAdapter;
import vn.kat.mp3playerfinal.Model.BaiHat;
import vn.kat.mp3playerfinal.R;
import vn.kat.mp3playerfinal.Service.APIService;
import vn.kat.mp3playerfinal.Service.DataService;

public class Fragment_Tim_Kiem extends Fragment {

    View view;
    AppBarLayout appbarLayoutSearchBaiHat;
    Toolbar toolbarSearchBaiHat;
    RecyclerView rcvSearchBaiHat;
    TextView tvKhongCoDuLieu;
    SearchBaiHatAdapter searchBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        appbarLayoutSearchBaiHat = view.findViewById(R.id.appbarLayoutSearchBaiHat);
        toolbarSearchBaiHat = view.findViewById(R.id.toolbarSearchBaiHat);
        rcvSearchBaiHat = view.findViewById(R.id.rcvSearchBaiHat);
        tvKhongCoDuLieu = view.findViewById(R.id.tvKhongCoDuLieu);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarSearchBaiHat);
        toolbarSearchBaiHat.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBaiHat(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchTuKhoaBaiHat(String query){
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetSearchBaiHat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                if (baiHatArrayList.size()>0){
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), baiHatArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    rcvSearchBaiHat.setLayoutManager(linearLayoutManager);
                    rcvSearchBaiHat.setAdapter(searchBaiHatAdapter);
                    tvKhongCoDuLieu.setVisibility(View.GONE);
                    rcvSearchBaiHat.setVisibility(View.VISIBLE);
                } else {
                    rcvSearchBaiHat.setVisibility(View.GONE);
                    tvKhongCoDuLieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
