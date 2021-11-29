package com.example.a201813709032_vize_odevi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;



import java.io.ByteArrayInputStream;
import java.util.ArrayList;



public class Rehber extends AppCompatActivity {

    public static  final int REQUEST_READ_CONTACTS = 1;
    ListView rehber_list;
    Button btn_smsEkrani;
    //menü ekrani
    boolean[] selectGrup;
    ArrayList<Integer> grupList = new ArrayList<>();
    String[] grupArray = {"Akraba", "Arkadaş", "İş"};

    ArrayList<Kisiler> kisiler = new ArrayList<Kisiler>();
    Setup setup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setup = Setup.getInstance(Rehber.this);
        setContentView(R.layout.activity_rehber);
        rehber_list=findViewById(R.id.lv_rehber);
        btn_smsEkrani=findViewById(R.id.btn_toSmsActivity);


        btn_smsEkrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Rehber.this, SmsEkrani.class);
                startActivity(i);
            }
        });


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {


            Cursor rehber = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,null);
            while(rehber.moveToNext()){
                @SuppressLint("Range") String isim = (String) rehber.getString(rehber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String numara = (String) rehber.getString(rehber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                @SuppressLint("Range") String contactID = rehber.getString(rehber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

                Kisiler r_nesnesi = new Kisiler();
                r_nesnesi.set_isim(isim);
                r_nesnesi.set_numara(numara);
                r_nesnesi.set_resim(ContactPhoto(contactID));
                kisiler.add(r_nesnesi);

            }
            rehber.close();

            KisilerAdapter kisilerAdapter = new KisilerAdapter(this, kisiler);
            if(rehber_list!=null)
            {
                rehber_list.setAdapter(kisilerAdapter);
            }
        }
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);

        //uzun tiklaninca cikacak menu
        rehber_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Rehber.this);
                builder.setTitle("Eklenecek Gruplar");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(grupArray, selectGrup, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            grupList.add(which);

                        }
                        if(!isChecked)
                            grupList.remove(which);
                    }
                });
                builder.setNegativeButton("kapat", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long index = parent.getItemIdAtPosition(position);
                        String kisi = kisiler.get((int)  index).get_isim();

                        for(int i = 0; i<grupList.size();i++){
                            if(grupArray[grupList.get(i)] == "Akraba") {
                                //setup.setValues(kisi, 1);
                                setup.setGrup_akraba(kisi);

                            }
                            if(grupArray[grupList.get(i)] == "Arkadaş") {
                                //setup.setValues(kisi, 2);
                               setup.setGrup_arkadas(kisi);
                            }
                            if(grupArray[grupList.get(i)] == "İş") {
                                //setup.setValues(kisi, 3);
                                setup.setGrup_is(kisi);
                            }
                        }


                    }
                });
                builder.show();

                return true;
            }

        });



    }
    public Bitmap ContactPhoto(String contactID){
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,Long.valueOf(contactID));
        Uri photoUri = Uri.withAppendedPath(contactUri,ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor cursor = getContentResolver().query(photoUri, new String[]{ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToNext();
            byte[] data = cursor.getBlob(0);
            if(data!=null)
                return BitmapFactory.decodeStream(new ByteArrayInputStream(data));
            else
                return null;
        }
        cursor.close();
        return null;
    }


}