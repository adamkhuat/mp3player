package vn.kat.mp3playerfinal.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.kat.mp3playerfinal.Model.QuangCao;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

}
