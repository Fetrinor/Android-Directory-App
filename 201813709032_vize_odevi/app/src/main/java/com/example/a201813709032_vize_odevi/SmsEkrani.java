package com.example.a201813709032_vize_odevi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class SmsEkrani extends AppCompatActivity {

    private RadioGroup radio_group;
    private RecyclerView rv_isimler;

    ArrayList<String> grup_akraba;
    ArrayList<String> grup_arkadas;
    ArrayList<String> grup_is;

    Setup setup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_ekrani);

        radio_group = (RadioGroup) findViewById(R.id.rb_group);
        rv_isimler = (RecyclerView) findViewById(R.id.rv_isimler);
        Button btn_smsgonder = (Button) findViewById(R.id.btn_smsgonder);
        setup = Setup.getInstance(SmsEkrani.this);

        grup_akraba = setup.getGrup_akraba();
        grup_arkadas = setup.getGrup_arkadas();
        grup_is = setup.getGrup_is();


        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = radio_group.getCheckedRadioButtonId();
                buildRecyclerView(selectedId);

                }
            }
        );

        btn_smsgonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SmsEkrani.this, "Mesaj gönderildi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildRecyclerView(int rb_secili){

        LinearLayoutManager manager = new LinearLayoutManager(this);
        switch(rb_secili) {
            case R.id.rb_akraba:
                AkrabaAdapter akrabaAdapter = new AkrabaAdapter(grup_akraba, SmsEkrani.this);
                rv_isimler.setHasFixedSize(true);
                rv_isimler.setLayoutManager(manager);
                rv_isimler.setAdapter(akrabaAdapter);
                break;
            case R.id.rb_arkadas:
                ArkadasAdapter arkadasAdapter = new ArkadasAdapter(grup_arkadas, SmsEkrani.this);
                rv_isimler.setHasFixedSize(true);
                rv_isimler.setLayoutManager(manager);
                rv_isimler.setAdapter(arkadasAdapter);
                break;
            case R.id.rb_is:
                IsAdapter isAdapter = new IsAdapter(grup_is, SmsEkrani.this);
                rv_isimler.setHasFixedSize(true);
                rv_isimler.setLayoutManager(manager);
                rv_isimler.setAdapter(isAdapter);
                break;
        }
    }

}