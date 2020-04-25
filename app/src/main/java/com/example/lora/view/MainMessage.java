package com.example.lora.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lora.Model.TbPengguna;
import com.example.lora.R;
import com.example.lora.controller.SQLLiteHelper;
import com.example.lora.dao.*;

import com.example.lora.recyleradapter.RecyclerViewAdapter;

public class MainMessage extends AppCompatActivity implements View.OnClickListener {

    Button add;

    SQLiteDatabase db;
    SQLLiteHelper helper;
    ImageButton btnPairing;

    BluetoothAdapter bta;
    public final static int REQUEST_ENABLE_BT = 1;

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    private String deviceAddress;

    TbPengguna tbp;
    TextView tvStatusPairing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);

        if (getIntent().getExtras()!= null){
            deviceAddress = getIntent().getStringExtra("device");
            Log.i("Androssss",deviceAddress);
        }

        getSupportActionBar().hide();

        add = findViewById(R.id.btnTambahPesan);
        recyclerView = findViewById(R.id.listRecylerView);
        btnPairing = findViewById(R.id.btnPairing);
        tvStatusPairing = findViewById(R.id.tvStatusPairing);

        bta = BluetoothAdapter.getDefaultAdapter();

        checkingBluetooth();

        helper = new SQLLiteHelper(this);
        db = helper.getReadableDatabase();
        tbp = new TbPengguna(db);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if (bta.isEnabled() == true){
            loadMessage();
        }

        btnPairing.setOnClickListener(this);
        add.setOnClickListener(this);

    }

    void loadMessage() {
        recyclerViewAdapter = new RecyclerViewAdapter(tbp.selectLastMessage(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void checkingBluetooth() {
        if (bta.isEnabled() == false) {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_ENABLE_BT){
            loadMessage();
        }else if (resultCode == RESULT_CANCELED && requestCode == REQUEST_ENABLE_BT){
            checkingBluetooth();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTambahPesan:
                startActivity(new Intent(MainMessage.this, MainActivity.class));
                finish();
                break;
            case R.id.btnPairing:
//                Toast.makeText(this, "Menuju ke activity pairing", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainMessage.this, DeviceListPairing.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
