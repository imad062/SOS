package com.example.imad.sos;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.DatabaseErrorHandler;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowContacts extends AppCompatActivity {

    public DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_show_contacts);

        final ListView listView;

        listView = (ListView) findViewById(R.id.listview_activity_show_contacts);
        listView.setClickable(true);

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

            final ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {

                    View view =super.getView(position, convertView, parent);

                    TextView textView=(TextView) view.findViewById(android.R.id.text1);


                    textView.setTextColor(Color.WHITE);

                    return view;
                }
            };
            listView.setAdapter(listAdapter);
        }

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ShowContacts.this, UpdateAndDeleteContacts.class);
                        intent.putExtra("id",position);
                        startActivity(intent);
                    }
                }
        );
    }
}
