package com.example.lora.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lora.R;
import com.example.lora.dao.myContacts;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private Context mContext;
    private List<myContacts> mProductList;

    public ProductListAdapter(Context mContext, List<myContacts> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.frame_contact, null);

        TextView tvNama = v.findViewById(R.id.tvNama);
        TextView tvNomor = v.findViewById(R.id.tvNomor);

        tvNama.setText(mProductList.get(position).getName());
        tvNomor.setText(mProductList.get(position).getNumber());

        v.setTag(mProductList.get(position).getNumber());

        return v;
    }

}
