package com.example.a201813709032_vize_odevi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ArkadasAdapter extends RecyclerView.Adapter<ArkadasAdapter.ViewHolder> {

    private ArrayList<String> grup_arkadas;

    private Context context;

    public ArkadasAdapter(ArrayList<String> grup, Context context){
        this.grup_arkadas = grup;
        this.context = context;
    }

    @NonNull
    @Override
    public ArkadasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isimler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArkadasAdapter.ViewHolder holder, int position) {
        String isim = grup_arkadas.get(position);
        holder.tv_isimler.setText(isim);
    }

    @Override
    public int getItemCount() {
        return grup_arkadas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_isimler;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_isimler = itemView.findViewById(R.id.isim);
        }
    }
}
