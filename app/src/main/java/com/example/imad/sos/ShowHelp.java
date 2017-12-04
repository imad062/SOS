package com.example.imad.sos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShowHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_help);
        getSupportActionBar().hide();
    }

    public void addContactsFromHelp(View view)
    {
        Intent intent = new Intent(this, AddContacts.class);
        startActivity(intent);
    }
}
