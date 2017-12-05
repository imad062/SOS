package com.example.imad.sos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAndDeleteContacts extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(UpdateAndDeleteContacts.this);

    EditText txtName, txtPhone;
    Button btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_contacts);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        int positionOfItem = intent.getIntExtra("id", 0);

        txtName = (EditText) findViewById(R.id.name_activity_updateDelete);
        txtPhone = (EditText) findViewById(R.id.phonenum_activity_updateDelete);

        btnDelete = (Button) findViewById(R.id.delete_activity_updateDelete);
        btnUpdate = (Button) findViewById(R.id.update_activity_updateDelete);

        Cursor data = dataBaseHelper.getAllData();
        //data.moveToFirst();
        while(data.moveToNext())
        {
            if(data.getString(0).equals(positionOfItem+1+""))
            {
                txtName.setText(data.getString(1));
                txtPhone.setText(data.getString(2));
            }
        }
        //txtName.setText(data.getString(1));
        //txtPhone.setText(data.getString(2));

        //Toast.makeText(UpdateAndDeleteContacts.this, positionOfItem + "", Toast.LENGTH_LONG).show();
    }
}
