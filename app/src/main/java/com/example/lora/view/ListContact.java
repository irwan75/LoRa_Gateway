package com.example.lora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.lora.R;
import com.example.lora.dao.allMessage;
import com.example.lora.dao.listContacts;
import com.example.lora.controller.ProductListAdapter;
import com.example.lora.recyleradapter.RVAdapterListContact;
import com.example.lora.recyleradapter.RecyclerViewAdapter;

public class ListContact extends AppCompatActivity {

    RecyclerView recyclerView;
    RVAdapterListContact rvAdapterListContact;
    ArrayList<listContacts> listAllContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact_full);

        loadContact();

        recyclerView = findViewById(R.id.listRecylerViewContact);
        rvAdapterListContact = new RVAdapterListContact(listAllContact, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapterListContact);

    }

    private void loadContact() {
        listAllContact = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String name = "";
        String phoneNumber = "";

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ", new String[]{id}, null);

                while (phoneCursor.moveToNext()) {
                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                listAllContact.add(new listContacts(name, phoneNumber));

                phoneCursor.close();
            }
            cursor.close();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ListContact.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
