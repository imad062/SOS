package com.example.imad.sos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAndDeleteContacts extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(UpdateAndDeleteContacts.this);

    EditText txtName, txtPhone;
    Button btnUpdate,btnDelete,btnGoToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_contacts);
        getSupportActionBar().hide();

        txtName = (EditText) findViewById(R.id.name_activity_updateDelete);
        txtPhone = (EditText) findViewById(R.id.phonenum_activity_updateDelete);

        btnDelete = (Button) findViewById(R.id.delete_activity_updateDelete);
        btnUpdate = (Button) findViewById(R.id.update_activity_updateDelete);
        btnGoToMain = (Button) findViewById(R.id.gotoMain_activity_updateDelete);

        Cursor data = dataBaseHelper.getAllData();
        Intent intent = getIntent();
        int positionOfItem = intent.getIntExtra("id", 0);


        while(data.moveToNext())
        {
            if(data.getString(0).equals(positionOfItem+1+""))
            {
                txtName.setText(data.getString(1));
                txtPhone.setText(data.getString(2));
            }
        }

        onUpdate();
        onDelete();
        onClickGoToMain();

    }

    public void onUpdate()
    {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = txtName.getText().toString();
                        String phone = txtPhone.getText().toString();


                        Intent intent = getIntent();
                        int positionOfItem = intent.getIntExtra("id", 0);

                        dataBaseHelper.updateData(positionOfItem+1+"",name,phone);

                        Toast.makeText(UpdateAndDeleteContacts.this, "Update Sucessfull", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void onDelete()
    {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        int positionOfItem = intent.getIntExtra("id", 0);

                        int numOfRows = dataBaseHelper.deleteData(positionOfItem+1+"");

                        Toast.makeText(UpdateAndDeleteContacts.this, "Deleted "+numOfRows+" rows",Toast.LENGTH_LONG).show();

                        Intent gotoShowContacts = new Intent(UpdateAndDeleteContacts.this ,ShowContacts.class);
                        startActivity(gotoShowContacts);
                    }
                }
        );
    }

    public void onClickGoToMain()
    {
        btnGoToMain.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UpdateAndDeleteContacts.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
