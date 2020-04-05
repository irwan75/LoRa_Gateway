package com.example.lora.recyleradapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lora.R;

import java.util.ArrayList;
import java.util.List;
import com.example.lora.dao.*;

import com.example.lora.view.MainActivity;

public class RVAdapterListContact extends RecyclerView.Adapter<RVAdapterListContact.ViewHolder> implements Filterable {

    private ArrayList<listContacts> dataListContact;
    private ArrayList<listContacts> dataListContactSearch;
    private Context context;
//    private etMessage etm = new etMessage();

    public RVAdapterListContact(ArrayList<listContacts> dataListContact, Context context) {
        this.dataListContact = dataListContact;
        dataListContactSearch = new ArrayList<>(dataListContact);
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
                context.startActivity(new Intent(context, MainActivity.class).putExtra("dataNomorListContact", holder.tvNomor.getText())
                .putExtra("dataNamaListContact", holder.tvNama.getText()));
//                etm.setNomor((String) holder.tvNomor.getText());
//                context.startActivity(new Intent(context, MainActivity.class));
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
//        return (dataListContact!= null) ? dataListContact.size() : 0;
        return dataListContact.size();
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<listContacts> filteredList = new ArrayList<>();

            if (constraint==null || constraint.length() == 0){
                filteredList.addAll(dataListContactSearch);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (listContacts list : dataListContactSearch){
                    if (list.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(list);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataListContact.clear();
            dataListContact.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
