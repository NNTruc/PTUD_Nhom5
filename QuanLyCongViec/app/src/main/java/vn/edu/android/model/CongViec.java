package vn.edu.android.model;

import java.io.Serializable;

/**
 * Created by Nguyen Trung Truc on 10/13/2016.
 */
public class CongViec implements Serializable {

    private int id;
    private String tenCongViec;
    private String moTa;
    private String ngayHT;
    private String gioHT;

    public CongViec() {
    }

    public CongViec(int id, String tenCongViec, String moTa, String ngayHT, String gioHT) {
        this.id = id;
        this.tenCongViec = tenCongViec;
        this.moTa = moTa;
        this.ngayHT = ngayHT;
        this.gioHT = gioHT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;

    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;

    }

    public String getNgayHT() {
        return ngayHT;
    }

    public void setNgayHT(String ngayHT) {
        this.ngayHT = ngayHT;

    }

    public String getGioHT() {
        return gioHT;
    }

    public void setGioHT(String gioHT) {
        this.gioHT = gioHT;
    }
}
