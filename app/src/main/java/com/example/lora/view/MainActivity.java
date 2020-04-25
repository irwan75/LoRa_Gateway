package com.example.lora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lora.Model.*;
import com.example.lora.R;
import com.example.lora.controller.SQLLiteHelper;
import com.example.lora.controller.bluetoothservice.*;
import com.example.lora.recyleradapter.RVAdapterMessage;
import com.example.lora.recyleradapter.RecyclerViewAdapter;

import java.util.ArrayList;
import com.example.lora.dao.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection, SerialListener {

    private enum Connected { False, Pending, True }

    SQLLiteHelper helper;
    SQLiteDatabase db;
    TbPengguna tbp;
    deviceAddressBluetooth dab;

    RecyclerView recyclerView;
    RVAdapterMessage rvAdapterMessage;
    ArrayList<loadMessage> listLoadMessage;

    EditText etNumber;
    ImageButton btnSend;
    ImageButton btnBack, btnAddNumber;

    public SerialSocket socket;
    public SerialService service;
    public String newline = "\r\n";

    private boolean initialStart = true;

    private Connected connected = Connected.False;

    String nomor = null;
    String nama = null;
//    TextView tvMessage;
    CardView cvMessagee;
    EditText etPesan;

    String deviceAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = findViewById(R.id.etNumber);
        btnSend = findViewById(R.id.btnSend);
        btnBack = findViewById(R.id.btnBack);
        btnAddNumber = findViewById(R.id.btnAddNumber);
        cvMessagee = findViewById(R.id.cvMessage);
        etPesan = findViewById(R.id.etText);
        recyclerView = findViewById(R.id.rvMessage);

        helper = new SQLLiteHelper(getApplicationContext());
        db = helper.getReadableDatabase();
        tbp = new TbPengguna(db);
        dab = new deviceAddressBluetooth(db);

        deviceAddress = dab.select().toString().trim();

        if (getIntent().getStringExtra("dataNomorListContact")!=null){
            nomor = getIntent().getStringExtra("dataNomorListContact").trim();
            nama = getIntent().getStringExtra("dataNamaListContact").trim();
            etNumber.setText(nomor+" ("+nama+")");
        }else if (getIntent().getStringExtra("NomorPilihan")!=null) {
            nomor = getIntent().getStringExtra("NomorPilihan").trim();
            nama = getIntent().getStringExtra("NamaPilihan").trim();
            etNumber.setText(nomor+" ("+nama+")");
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        loadMessage(nomor);

        btnSend.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnAddNumber.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, MainMessage.class));
        finish();
    }

    void loadMessage(String nomor){
        rvAdapterMessage = new RVAdapterMessage(tbp.select(nomor),this);
        recyclerView.setAdapter(rvAdapterMessage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSend :
                try {
                    if (((etPesan.getText().toString().trim()).equals("")) || ((etNumber.getText().toString().trim()).equals(""))) {
                        Toast.makeText(MainActivity.this, "Pesan atau nomor masih kosong", Toast.LENGTH_SHORT).show();
                    }else if (nama == null){
                        Toast.makeText(MainActivity.this, ""+tbp.insertDataTanpaNama(nomor, etPesan.getText().toString().trim()), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "" + tbp.insertData(nama, nomor, etPesan.getText().toString().trim()), Toast.LENGTH_SHORT).show();
                        send(etPesan.getText().toString().trim());
                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ""+ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                loadMessage(nomor);
                etPesan.setText("");
            break;
            case R.id.btnBack :
                startActivity(new Intent(MainActivity.this, MainMessage.class));
                finish();
                break;
            case R.id.btnAddNumber :
                Intent intent = new Intent(MainActivity.this, ListContact.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        service = ((SerialService.SerialBinder) binder).getService();
//        if(initialStart && isResumed()) {
//            initialStart = false;
            MainActivity.this.runOnUiThread(this::connect);
//        }
    }

    //Bluetooth Send Service

    private void connect() {
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);
            String deviceName = device.getName() != null ? device.getName() : device.getAddress();
//            status("connecting...");
            connected = Connected.Pending;
            socket = new SerialSocket();
            service.connect(this, "Connected to " + deviceName);
            socket.connect(getApplicationContext(), service, device);
        } catch (Exception e) {
            onSerialConnectError(e);
        }
    }

    private void disconnect() {
        connected = Connected.False;
        service.disconnect();
        socket.disconnect();
        socket = null;
    }

    private void send(String str) {
        if(connected != Connected.True) {
            Toast.makeText(getApplicationContext(), "not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
//            SpannableStringBuilder spn = new SpannableStringBuilder(str+'\n');
//            spn.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorSendText)), 0, spn.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            receiveText.append(spn);
            byte[] data = (str + newline).getBytes();
            socket.write(data);
            socket.write(data);
        } catch (Exception e) {
            onSerialIoError(e);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        service = null;
    }

    @Override
    public void onSerialConnect() {
//        status("connected");
        connected = Connected.True;
    }

    @Override
    public void onSerialConnectError(Exception e) {
//        status("connection failed: " + e.getMessage());
        disconnect();
    }

    @Override
    public void onSerialRead(byte[] data) {

    }

    @Override
    public void onSerialIoError(Exception e) {
//        status("connection lost: " + e.getMessage());
        disconnect();
    }
}

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please Wait....");

//        try {
//            btnBack.setBackgroundColor(Color.parseColor("#008577"));
//            Drawable drawable = ContextCompat.getDrawable(getResources(), R.drawable.background_card_color);
//            tvMessage.setBackground(drawable);
//        }catch (Exception ex){
//            Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_LONG).show();
//        }
