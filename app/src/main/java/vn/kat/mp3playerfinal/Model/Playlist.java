package vn.kat.mp3playerfinal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

    @SerializedName("IDPlaylist")
    @Expose
    private String iDPlaylist;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("HinhIcon")
    @Expose
    private String hinhIcon;

    public String getIDPlaylist() {
        return iDPlaylist;
    }

    public void setIDPlaylist(String iDPlaylist) {
        this.iDPlaylist = iDPlaylist;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getHinhIcon() {
        return hinhIcon;
    }

    public void setHinhIcon(String hinhIcon) {
        this.hinhIcon = hinhIcon;
    }

}
