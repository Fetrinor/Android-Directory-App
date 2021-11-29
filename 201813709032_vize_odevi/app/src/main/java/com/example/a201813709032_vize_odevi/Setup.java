package com.example.a201813709032_vize_odevi;

import android.content.Context;

import java.util.ArrayList;


public class Setup {



    Context context;


    String isim;
    ArrayList<String> grup_akraba = new ArrayList<String>(); //grup index 1
    ArrayList<String> grup_arkadas = new ArrayList<String>(); //grup index 2
    ArrayList<String> grup_is = new ArrayList<String>(); // grup index 3


    static Setup setup = null;

    private Setup(Context context){
        this.context = context;


    }

    public static Setup getInstance(Context context){
        if(setup == null){
            setup = new Setup(context);
        }
        return setup;
    }



    public ArrayList<String> getGrup_akraba(){
        return  grup_akraba;
    }
    public void setGrup_akraba(String akraba){
        grup_akraba.add(akraba);
    }

    public ArrayList<String> getGrup_arkadas() {
        return grup_arkadas;
    }
    public void setGrup_arkadas(String arkadas){
        grup_arkadas.add(arkadas);
    }

    public ArrayList<String> getGrup_is() {
        return grup_is;
    }
    public void setGrup_is(String is){
        grup_is.add(is);
    }
}
