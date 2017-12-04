package com.example.imad.sos;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Telephony;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    Button addContacts, showContacts, sos;

    String geoLocationApiKey = "AIzaSyCHYtHvlr1VVEy96yw0GmudX6-sANMT9tQ";

    String locationString = "";

    String longlat;

    String message = "My life is in danger. My Location: ";

    Boolean messageCreated = false;

    RequestQueue requestQueue;

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

        asyncAddressGetter getAddress = new asyncAddressGetter();
        getAddress.execute("no");

        sos.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(MainActivity.this, locationString, Toast.LENGTH_LONG).show();

                        //////
                        int sentCount = 0;
                        Cursor data = dataBaseHelper.getPhoneNumbers();

                        while(data.moveToNext())
                        {
                            if( isPhoneNum( data.getString(0) ) )
                            {
                                String phoneNum = data.getString(0);
                                SmsSender smsSender = new SmsSender();
                                smsSender.sendSms(phoneNum,message);
                                sentCount++;
                            }
                            //Toast.makeText(MainActivity.this, data.getString(0),Toast.LENGTH_LONG).show();
                            //Toast.makeText(MainActivity.this, isPhoneNum(data.getString(0))+"", Toast.LENGTH_LONG).show();

                        }
                        Toast.makeText(MainActivity.this, "SOS sent to " + sentCount + " contacts ", Toast.LENGTH_LONG).show();

                        //////

                    }
                }
        );

    }

    private class asyncAddressGetter extends AsyncTask<String,Void,String>
    {
        public Void createMessage(String location)
        {
            message = message + location + " . Please Help me.";
            messageCreated = true;
            locationString = location;
            return null;
        }

        public String getLocation(Context context) {
            int status = context.getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                    context.getPackageName());
            if (status == PackageManager.PERMISSION_GRANTED) {
                LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                List<String> providers = mgr.getAllProviders();
                if (providers != null && providers.contains(LocationManager.NETWORK_PROVIDER)) {
                    Location loc = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (loc != null) {
                        return loc.getLatitude() + "," + loc.getLongitude();
                    }
                }
            }
            return null;
        }

        @Override
        protected String doInBackground(String... params) {

            longlat = getLocation(MainActivity.this);

            requestQueue = Volley.newRequestQueue(MainActivity.this);

            String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+longlat+"&key=AIzaSyCHYtHvlr1VVEy96yw0GmudX6-sANMT9tQ";

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url , new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try{
                                String location = response.getJSONArray("results").getJSONObject(0).getString("formatted_address");
                                createMessage(location);
                                //Toast.makeText(MainActivity.this, locationString, Toast.LENGTH_LONG).show();
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            requestQueue.add(jsObjRequest);

            return null;
        }
    }

    public boolean isPhoneNum(String phoneNum)
    {
        if(phoneNum.length()!=11)
        {
            return false;
        }
        else
        {
            for(int i = 0; i < 11; i++)
            {
                if(phoneNum.charAt(i) < '0' || phoneNum.charAt(i) > '9')
                {
                    return false;
                }
            }
            return true;
        }
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
