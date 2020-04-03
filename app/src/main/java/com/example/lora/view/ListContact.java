package com.example.lora.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.lora.R;
import com.example.lora.dao.myContacts;
import com.example.lora.controller.ProductListAdapter;

public class ListContact extends AppCompatActivity {

    private ListView lsView;
    private ProductListAdapter adapter;
    private List<myContacts> mProductList;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact_full);

        lsView = findViewById(R.id.listview);
        mProductList = new ArrayList<>();

        loadContact();

        lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ListContact.this, ""+view.getTag(), Toast.LENGTH_SHORT).show();
//                nmb.setNumber(String.valueOf(view.getTag()));
                Intent intent = new Intent(ListContact.this, MainActivity.class);
                intent.putExtra("dataNomor", ""+view.getTag());
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadContact(){
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null,null,null,null);
        String name = "";
        String phoneNumber = "";

        if (cursor.getCount()>0){
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ", new String[]{id}, null);

                while (phoneCursor.moveToNext()){
                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }

                mProductList.add(new myContacts(i+1, ""+name, ""+phoneNumber));
                i++;

                phoneCursor.close();
            }
            try {
                adapter = new ProductListAdapter(getApplicationContext(), mProductList);
            }catch (Exception ex){
                Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
        lsView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ListContact.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
