package com.example.lora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import com.example.lora.R;
import com.example.lora.controller.bluetoothservice.*;

public class DeviceListPairing extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list_pairing);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentList, new DevicesFragment(), "devices").commit();
        else
            onBackStackChanged();

    }

    @Override
    public void onBackStackChanged() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount()>0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DeviceListPairing.this, MainMessage.class));
        finish();
    }


}
