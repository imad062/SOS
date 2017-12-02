package com.example.imad.sos;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Button addContacts, showContacts, sos;

    public DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        addContacts = (Button) findViewById(R.id.add_contacts_activity_main);
        showContacts = (Button) findViewById(R.id.show_contacts_activity);
        sos = (Button) findViewById(R.id.sos_activity_main);

        dataBaseHelper = new DataBaseHelper(this);

    }

    public void addContacts(View view)
    {
        Intent intent = new Intent(this, AddContacts.class);
        startActivity(intent);
    }

    public void showContacts(View view)
    {
        Intent intent = new Intent(this, ShowContacts.class);
        startActivity(intent);
    }



}
