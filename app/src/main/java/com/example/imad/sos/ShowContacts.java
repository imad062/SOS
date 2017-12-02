package com.example.imad.sos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowContacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_show_contacts);
    }
}
