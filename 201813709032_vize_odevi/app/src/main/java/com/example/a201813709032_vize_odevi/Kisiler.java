package com.example.a201813709032_vize_odevi;

import android.graphics.Bitmap;

public class Kisiler {

    private String isim;
    private String numara;
    private Bitmap resim = null;

    public void set_resim(Bitmap resim){this.resim=resim;}
    public Bitmap get_resim() { return resim;}

    public void set_isim(String isim){this.isim=isim;}
    public String get_isim(){return isim;}

    public void set_numara(String numara){this.numara=numara;}
    public String get_numara(){return numara;}

}
