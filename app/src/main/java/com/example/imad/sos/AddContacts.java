package com.example.imad.sos;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContacts extends AppCompatActivity {

    public DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
    EditText txtName, txtPhone;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        getSupportActionBar().hide();

        txtName = (EditText) findViewById(R.id.name_activity_addcontacts);
        txtPhone = (EditText) findViewById(R.id.phone_activity_addcontacts);

        btnSave = (Button) findViewById(R.id.save_activity_addcontacts);

        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean insertSuccess;

                        String name = txtName.getText().toString();
                        String phone = txtPhone.getText().toString();

                        if(name.equals("") || phone.equals(""))
                        {
                            Toast.makeText(AddContacts.this, "One of the fields is empty",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            insertSuccess = dataBaseHelper.insertData(name, phone);
                            if(insertSuccess == true)
                            {
                                Toast.makeText(AddContacts.this, "Insertion Successful", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(AddContacts.this, "Insertion Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );

    }
}
