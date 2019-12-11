package vn.kat.mp3playerfinal.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.kat.mp3playerfinal.Model.Album;
import vn.kat.mp3playerfinal.Model.BaiHat;
import vn.kat.mp3playerfinal.Model.ChuDe;
import vn.kat.mp3playerfinal.Model.ChuDeVaTheLoai;
import vn.kat.mp3playerfinal.Model.Playlist;
import vn.kat.mp3playerfinal.Model.QuangCao;
import vn.kat.mp3playerfinal.Model.TheLoai;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeVaTheLoai> GetChuDeVaTheLoai();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbum();

    @GET("baihatduocyeuthich.php")
    Call<List<BaiHat>> GetBaiHat();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoPlaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhSachPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetDanhSachChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

}
