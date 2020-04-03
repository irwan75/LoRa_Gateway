package com.example.lora.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lora.R;
import com.example.lora.dao.myContacts;

import java.util.List;

public class RecentMessageListAdapter extends BaseAdapter {

    private Context mContext;
    private List<myContacts> mProductList;

    public RecentMessageListAdapter(Context mContext, List<myContacts> mProductList) {
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
        View v = View.inflate(mContext, R.layout.frame_recent_message, null);

        TextView tvMessageRecent = v.findViewById(R.id.tvMessageRecent);
        TextView tvNamaRecent = v.findViewById(R.id.tvNamaRecent);
        TextView tvTimeRecent = v.findViewById(R.id.tvTimeRecent);

        tvNamaRecent.setText(mProductList.get(position).getName());
        tvMessageRecent.setText(mProductList.get(position).getNumber());
        tvTimeRecent.setText(mProductList.get(position).getId());

        v.setTag(mProductList.get(position).getId());

        return v;
    }
}
