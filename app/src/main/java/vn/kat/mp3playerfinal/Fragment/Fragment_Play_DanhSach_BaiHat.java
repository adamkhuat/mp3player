package vn.kat.mp3playerfinal.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.kat.mp3playerfinal.Activity.PlayMusicActivity;
import vn.kat.mp3playerfinal.Adapter.PlayMusicAdapter;
import vn.kat.mp3playerfinal.R;

public class Fragment_Play_DanhSach_BaiHat extends Fragment {

    View view;
    RecyclerView rcvPlayDanhsachBaiHat;
    PlayMusicAdapter playMusicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsach_baihat, container, false);
        rcvPlayDanhsachBaiHat = view.findViewById(R.id.rcvPlayDanhsachBaiHat);
        if (PlayMusicActivity.baiHatArrayList.size() > 0) {
            playMusicAdapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.baiHatArrayList);
            rcvPlayDanhsachBaiHat.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcvPlayDanhsachBaiHat.setAdapter(playMusicAdapter);
        }

        return view;
    }
}
