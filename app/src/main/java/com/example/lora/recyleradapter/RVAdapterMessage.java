package com.example.lora.recyleradapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lora.R;
import com.example.lora.dao.loadMessage;

import java.util.ArrayList;

public class RVAdapterMessage extends RecyclerView.Adapter<RVAdapterMessage.ViewHolder> {

    private ArrayList<loadMessage> dataListMessage;
    private Context context;

    public RVAdapterMessage(ArrayList<loadMessage> dataListMessage, Context context) {
        this.dataListMessage = dataListMessage;
        this.context = context;
    }

    @Override
    public RVAdapterMessage.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.frame_message, parent,false);
        return new RVAdapterMessage.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVAdapterMessage.ViewHolder holder, int position) {
//        holder.tvTgldanNama.setText(dataListMessage.get(position).getTglwaktu());
//        holder.tvMessage.setText(dataListMessage.get(position).getMessage());
        loadMessage lMessage = dataListMessage.get(position);
        String tgl_dan_waktu = lMessage.getTglwaktu();
        String pesan = lMessage.getMessage();
        String rule = lMessage.getRule();

        holder.tvTgldanNama.setText(tgl_dan_waktu);
        holder.tvMessage.setText(pesan);
        if (rule.equals("sender")){
            holder.tvMessage.setBackgroundResource(R.drawable.background_card_color);
            holder.tvMessage.setTextColor(Color.parseColor("#FCFAFA"));
            holder.relativeLayout.setPadding(150, 0, 0, 0);
            holder.tvTgldanNama.setLayoutParams(holder.params);
        }else {
            holder.tvTgldanNama.setLayoutParams(holder.paramss);
            holder.relativeLayout.setPadding(0,0,150,0);
            holder.tvMessage.setBackgroundResource(R.drawable.background_card);
            holder.tvMessage.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        return (dataListMessage!= null) ? dataListMessage.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTgldanNama, tvMessage;
        CardView cvMessage;
        RelativeLayout relativeLayout;
        RelativeLayout.LayoutParams params;
        RelativeLayout.LayoutParams paramss;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTgldanNama = itemView.findViewById(R.id.tgl_dan_waktu_message);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            cvMessage = itemView.findViewById(R.id.cvMessage);
            relativeLayout = itemView.findViewById(R.id.rlLayout);

            params = (RelativeLayout.LayoutParams)tvTgldanNama.getLayoutParams();
            paramss = (RelativeLayout.LayoutParams) tvTgldanNama.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            paramss.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }
    }
}
