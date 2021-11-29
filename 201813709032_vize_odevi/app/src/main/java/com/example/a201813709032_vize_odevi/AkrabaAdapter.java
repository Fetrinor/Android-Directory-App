package com.example.a201813709032_vize_odevi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AkrabaAdapter extends RecyclerView.Adapter<AkrabaAdapter.ViewHolder> {

    private ArrayList<String> grup_akraba;;
    private Context context;

    public AkrabaAdapter(ArrayList<String> grup, Context context){
                this.grup_akraba = grup;
                this.context = context;
    }

    @NonNull
    @Override
    public AkrabaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isimler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AkrabaAdapter.ViewHolder holder, int position) {
        String isim = grup_akraba.get(position);
        holder.tv_isimler.setText(isim);
    }

    @Override
    public int getItemCount() {
        return grup_akraba.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_isimler;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_isimler = itemView.findViewById(R.id.isim);
        }
    }
}
