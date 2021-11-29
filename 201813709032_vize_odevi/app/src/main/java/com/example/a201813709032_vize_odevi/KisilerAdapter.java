package com.example.a201813709032_vize_odevi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class KisilerAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;
    private ArrayList<Kisiler> KisilerList;

    public KisilerAdapter(Activity activity, ArrayList<Kisiler> KisilerList){
        this.mInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.KisilerList = KisilerList;
    }

    @Override
    public int getCount(){
        return KisilerList.size();
    }
    @Override
    public Object getItem(int position){
        return KisilerList.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.kisi, null);
        TextView kisi_ad = (TextView) convertView.findViewById(R.id.tv_rehber_isim);
        TextView kisi_no = (TextView) convertView.findViewById(R.id.tv_rehber_no);
        ImageView kisi_resim = (ImageView) convertView.findViewById(R.id.iv_resim);
        Kisiler kisi = KisilerList.get(position);
        kisi_ad.setText(kisi.get_isim());
        kisi_no.setText(kisi.get_numara());

        if(kisi.get_resim()!=null)
            kisi_resim.setImageBitmap(kisi.get_resim());
        else
            kisi_resim.setImageResource(R.drawable.ic_launcher_background);

        return  convertView;
    }
}
