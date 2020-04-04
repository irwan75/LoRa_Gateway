package com.example.lora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lora.R;
import com.example.lora.dao.allMessage;
import com.example.lora.recyleradapter.RVAdapterMessage;
import com.example.lora.recyleradapter.RecyclerViewAdapter;

import java.util.ArrayList;
import com.example.lora.dao.loadMessage;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RVAdapterMessage rvAdapterMessage;
    ArrayList<loadMessage> listLoadMessage;

    EditText etNumber;
    ImageButton btnSend;
    ImageButton btnBack, btnAddNumber;

    ProgressDialog progressDialog;

    String number;

//    TextView tvMessage;
    CardView cvMessagee;

    String[] tgl_dan_waktu = {"1020892","21u3921","921829","892172","909018","9032121","0318922","92381290","2918021","2891082"};
    String[] message = {"90knsakjdsjabdabchjbsahcbhcbhasbchjbasjcbashcnjw",
            "dcascascsacascsjkqncqbnicbwbcwiooadnas","dokcsjhcbhwebcbwejbvhwbevadnsa","dcsajkcnjabchjbwhcbwcewvjhwebviosajd",
            "cjkdnckwevhbrevbrevbrbevburedioasdna","disancndscbdsbvchjdbsvyubewiuvbeoasjda","dscejwnciwebviuebwvbewivbuiewbvaonda"
            ,"diocnewivbuwebvybewuyvbweibcewjbcjkwvewsad","dioacnjksncoqwnciwhevweivbiewsada","dsdcwkllcjkwnecpqcniwenvibewhabd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumber = findViewById(R.id.etNumber);
        btnSend = findViewById(R.id.btnSend);
        btnBack = findViewById(R.id.btnBack);
        btnAddNumber = findViewById(R.id.btnAddNumber);
//        tvMessage = findViewById(R.id.tvMessage);
        cvMessagee = findViewById(R.id.cvMessage);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");

//        try {
//            btnBack.setBackgroundColor(Color.parseColor("#008577"));
//            Drawable drawable = ContextCompat.getDrawable(getResources(), R.drawable.background_card_color);
//            tvMessage.setBackground(drawable);
//        }catch (Exception ex){
//            Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_LONG).show();
//        }

        if (getIntent().getStringExtra("dataNomor")==null) {
            etNumber.setHint("Masih Kosong");
        }else{
            etNumber.setText(getIntent().getStringExtra("dataNomor"));
        }if (getIntent().getStringExtra("Nomor")!=null) {
            number = getIntent().getStringExtra("Nomor").toString().trim();
        }

        loadMessage();

        recyclerView = findViewById(R.id.rvMessage);

        rvAdapterMessage = new RVAdapterMessage(listLoadMessage, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapterMessage);

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
                startActivity(intent);
                finish();
//                progressDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, MainMessage.class));
        finish();
    }

    void loadMessage(){
        listLoadMessage = new ArrayList<>();
        for (int i=0 ; i<tgl_dan_waktu.length;i++){
            listLoadMessage.add(new loadMessage(tgl_dan_waktu[i], message[i]));
        }
    }

}
