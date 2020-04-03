package com.example.lora.recyleradapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lora.R;

import java.util.ArrayList;
import com.example.lora.dao.allMessage;
import com.example.lora.view.MainActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<allMessage> dataList;
    private Context context;

    public RecyclerViewAdapter(ArrayList<allMessage> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.frame_all_message, parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.Tvnama_nomor.setText(dataList.get(position).getNama_nomor());
        holder.Tvpesan.setText(dataList.get(position).getPesan());
        holder.Tvwaktu.setText(dataList.get(position).getWaktu());

        //jika text viewnya yang di klik
        /*holder.Tvwaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(name);
            }
        });*/

        holder.cardViewAllMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MainActivity.class).putExtra("Nomor", holder.Tvnama_nomor.getText()));
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList!= null) ? dataList.size() : 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView Tvnama_nomor, Tvpesan, Tvwaktu;
        CardView cardViewAllMessage;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            Tvnama_nomor = itemView.findViewById(R.id.tvNamaatauNomor);
            Tvpesan = itemView.findViewById(R.id.tvPesan);
            Tvwaktu = itemView.findViewById(R.id.tvTanggaldanWaktu);
            cardViewAllMessage = itemView.findViewById(R.id.cardViewAllMessage);
        }

    }

    /*public void remove(String item) {
        int position = rvData.indexOf(item);
        rvData.remove(position);
        notifyItemRemoved(position);
    }*/

}
