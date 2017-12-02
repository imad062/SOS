package com.example.imad.sos;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button addContacts, showContacts, sos;

    DataBaseHelper dataBaseHelper;

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
        addContacts.setOnClickListener(
                new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v) {

                    }
                }
        );

    }
}
