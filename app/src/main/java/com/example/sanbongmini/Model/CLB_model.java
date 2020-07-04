package com.example.sanbongmini.Model;

public class CLB_model {
     private  int hinhclb;
     private  String mota1clb, mota2clb;

    public CLB_model(int hinhclb, String mota1clb, String mota2clb) {
        this.hinhclb = hinhclb;
        this.mota1clb = mota1clb;
       this.mota2clb = mota2clb;
    }

    public int getHinhclb() {
        return hinhclb;
    }

    public void setHinhclb(int hinhclb) {
        this.hinhclb = hinhclb;
    }

    public String getMota1clb() {
        return mota1clb;
    }

    public void setMota1clb(String mota1clb) {
        this.mota1clb = mota1clb;
    }

    public String getMota2clb() {
        return mota2clb;
    }

    public void setMota2clb(String mota2clb) {
        this.mota2clb = mota2clb;
    }
}
