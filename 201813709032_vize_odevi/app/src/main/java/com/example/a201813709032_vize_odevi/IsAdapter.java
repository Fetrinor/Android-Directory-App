package com.example.a201813709032_vize_odevi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class IsAdapter extends RecyclerView.Adapter<IsAdapter.ViewHolder> {

    private ArrayList<String> grup_is;

    private Context context;

    public IsAdapter(ArrayList<String> grup, Context context){
        this.grup_is = grup;
        this.context = context;
    }

    @NonNull
    @Override
    public IsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isimler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IsAdapter.ViewHolder holder, int position) {
        String isim = grup_is.get(position);
        holder.tv_isimler.setText(isim);
    }

    @Override
    public int getItemCount() {
        return grup_is.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_isimler;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_isimler = itemView.findViewById(R.id.isim);
        }
    }
}
