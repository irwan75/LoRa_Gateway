package com.example.lora.recyleradapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lora.R;

import java.util.ArrayList;
import com.example.lora.dao.listContacts;
import com.example.lora.view.MainActivity;

public class RVAdapterListContact extends RecyclerView.Adapter<RVAdapterListContact.ViewHolder> {

    private ArrayList<listContacts> dataListContact;
    private Context context;

    public RVAdapterListContact(ArrayList<listContacts> dataListContact, Context context) {
        this.dataListContact = dataListContact;
        this.context = context;
    }

    @Override
    public RVAdapterListContact.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.frame_list_contact, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RVAdapterListContact.ViewHolder holder, final int position) {
        holder.tvNama.setText(dataListContact.get(position).getName());
        holder.tvNomor.setText(dataListContact.get(position).getNumber());
        holder.CVListContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MainActivity.class).putExtra("dataNomor", holder.tvNomor.getText()));
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataListContact!= null) ? dataListContact.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvNomor;
        CardView CVListContact;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaContact);
            tvNomor = itemView.findViewById(R.id.tvNomorHandphone);
            CVListContact = itemView.findViewById(R.id.cvListContact);
        }
    }
}
