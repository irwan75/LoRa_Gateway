package com.example.lora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lora.R;
import com.example.lora.fragment.FragListRecentMessage;

public class MainActivity extends AppCompatActivity {

    EditText etNumber;
    ImageButton btnSend;
    FragListRecentMessage fragRecMess;
    ImageButton btnBack, btnAddNumber;

    ProgressDialog progressDialog;

    String data_ambil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumber = findViewById(R.id.etNumber);
        btnSend = findViewById(R.id.btnSend);
        btnBack = findViewById(R.id.btnBack);
        btnAddNumber = findViewById(R.id.btnAddNumber);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");


        data_ambil = getIntent().getStringExtra("Nomor");

        if (getIntent().getStringExtra("dataNomor")==null) {
//            etNumber.setHint("Masih Kosong");
        }else {
            etNumber.setText(getIntent().getStringExtra("dataNomor"));
        }

        FragmentRecentMessage();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "terklikji kirim", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainMessage.class));
                finish();
            }
        });

        btnAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListContact.class);
                progressDialog.show();
                startActivity(intent);
                finish();
//                progressDialog.dismiss();
            }
        });
    }

    void FragmentRecentMessage(){
        fragRecMess = new FragListRecentMessage();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layRecentMessage, fragRecMess);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, MainMessage.class));
        finish();
    }

}
