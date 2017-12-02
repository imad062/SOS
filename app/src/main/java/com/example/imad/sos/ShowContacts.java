package com.example.imad.sos;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.DatabaseErrorHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowContacts extends AppCompatActivity {

    public DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_show_contacts);

        listView = (ListView) findViewById(R.id.listview_activity_show_contacts);
        Cursor data = dataBaseHelper.getAllData();
        ArrayList<String> arrayList = new ArrayList<>();

        if(data.getCount() == 0)
        {
            Toast.makeText(this, "No data to show", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            while (data.moveToNext())
            {
                arrayList.add("Name : " + data.getString(1) + " Phone: " + data.getString(2));
            }

            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(listAdapter);
        }
    }
}
