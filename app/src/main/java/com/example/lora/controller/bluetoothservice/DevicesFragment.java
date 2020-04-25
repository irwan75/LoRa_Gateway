package com.example.lora.controller.bluetoothservice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.Collections;
import com.example.lora.R;
import com.example.lora.view.MainMessage;
import com.example.lora.dao.*;

public class DevicesFragment extends ListFragment {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayList<BluetoothDevice> listItems = new ArrayList<>();
    private ArrayAdapter<BluetoothDevice> listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH))
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listAdapter = new ArrayAdapter<BluetoothDevice>(getActivity(), 0, listItems) {
            @Override
            public View getView(int position, View view, ViewGroup parent) {
                BluetoothDevice device = listItems.get(position);
                if (view == null)
                    view = getActivity().getLayoutInflater().inflate(R.layout.dialog_list_pairing_bluetooth, parent, false);
                TextView text1 = view.findViewById(R.id.text1);
                TextView text2 = view.findViewById(R.id.text2);
                text1.setText(device.getName());
                text2.setText(device.getAddress());
                return view;
            }
        };
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(null);
        View header = getActivity().getLayoutInflater().inflate(R.layout.device_list_header, null, false);
        getListView().addHeaderView(header, null, false);
        setEmptyText("initializing...");
        ((TextView) getListView().getEmptyView()).setTextSize(18);
        setListAdapter(listAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(bluetoothAdapter == null)
            setEmptyText("<bluetooth not supported>");
        else if(!bluetoothAdapter.isEnabled())
            setEmptyText("<bluetooth is disabled>");
        else
            setEmptyText("<no bluetooth devices found>");
        refresh();
    }

    void refresh() {
        listItems.clear();
        if(bluetoothAdapter != null) {
            for (BluetoothDevice device : bluetoothAdapter.getBondedDevices())
                if (device.getType() != BluetoothDevice.DEVICE_TYPE_LE)
                    listItems.add(device);
        }
        Collections.sort(listItems, DevicesFragment::compareTo);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        BluetoothDevice device = listItems.get(position-1);
        Intent intent = new Intent(getActivity(), MainMessage.class);
        intent.putExtra("device", device.getAddress());
        startActivity(intent);
        getActivity().finish();
    }



    /**
     * sort by name, then address. sort named devices first
     */
    static int compareTo(BluetoothDevice a, BluetoothDevice b) {
        boolean aValid = a.getName()!=null && !a.getName().isEmpty();
        boolean bValid = b.getName()!=null && !b.getName().isEmpty();
        if(aValid && bValid) {
            int ret = a.getName().compareTo(b.getName());
            if (ret != 0) return ret;
            return a.getAddress().compareTo(b.getAddress());
        }
        if(aValid) return -1;
        if(bValid) return +1;
        return a.getAddress().compareTo(b.getAddress());
    }

}
