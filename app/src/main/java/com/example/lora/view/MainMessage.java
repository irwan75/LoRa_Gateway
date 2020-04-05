package com.example.lora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lora.R;
import com.example.lora.dao.allMessage;
import java.util.ArrayList;

import com.example.lora.recyleradapter.RecyclerViewAdapter;

public class MainMessage extends AppCompatActivity {

    Button add;

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<allMessage> listAllMessage;

    String[] nama = {"Ardi","Dian","Andi","Irwan","Lia","Icha","Nita","Andri","Dandi","Nani"};
    String[] number = {"1020892","21u3921","921829","892172","909018","9032121","0318922","92381290","2918021","2891082"};
    String[] chat = {"90knsakjcnjw","diooadnas","dokadnsa","diosajd","dioasdna","dioasjda","dsaonda","diosad","diosada","dsdhabd"};
    String[] date_time = {"dsajdka","djsadw","dionqo","diowej","oew0239","903dfjkwef","dowqdq","odiqjd","powqdj","9201809"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);

        add = findViewById(R.id.btnTambahPesan);
        loadMessage();

        recyclerView = findViewById(R.id.listRecylerView);

        recyclerViewAdapter = new RecyclerViewAdapter(listAllMessage, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMessage.this, MainActivity.class));
                finish();
            }
        });

    }

    void loadMessage(){
        listAllMessage = new ArrayList<>();
        for (int i=0 ; i<number.length;i++){
            listAllMessage.add(new allMessage(nama[i], number[i], chat[i], date_time[i]));
        }
    }
}
