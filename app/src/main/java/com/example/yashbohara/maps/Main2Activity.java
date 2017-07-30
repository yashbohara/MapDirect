package com.example.yashbohara.maps;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.yashbohara.maps.MainActivity.flag;
import static com.example.yashbohara.maps.MainActivity.lat1;
import static com.example.yashbohara.maps.MainActivity.lng1;

public class Main2Activity extends AppCompatActivity {
    static double a, b;
    CustomAdapter object;
    ListView l1;
    TextView t1, t2;
    JSONArray a1;
    JSONObject o1;
    static Context c;
    static Bundle bb;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        c = Main2Activity.this;
        bb = savedInstanceState;
        Bundle bundle = getIntent().getExtras();
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        l1 = (ListView) findViewById(R.id.l1);
        b1 = (Button) findViewById(R.id.button);
        new ttt2(lat1, lng1).execute();
        object = new CustomAdapter(this, R.layout.row, Main2Activity.this);
        l1.setAdapter(object);
        String s = bundle.getString("js");
        a = (bundle.getDouble("l1"));
        b = (bundle.getDouble("l2"));
        String sp = bundle.getString("spinner");
        Log.e("latitude", "" + a);
        Log.e("longitude", "" + b);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, new fragmentone());
        fragmentTransaction.commit();

        try {
            o1 = new JSONObject(s);
            a1 = o1.getJSONArray("results");
            int c = 0;
            while (c < a1.length()) {
                JSONObject o2 = a1.getJSONObject(c);
                JSONObject o3 = o2.getJSONObject("geometry");
                JSONObject o4 = o3.getJSONObject("location");
                float f = Float.parseFloat(o4.getString("lat"));
                float f1 = Float.parseFloat(o4.getString("lng"));
                String s2 = o2.getString("name");
                Log.e("jjjjjjjjjjjjjjjjj", s2);
                gs obj = new gs(f, f1, s2);
                obj.setLat(f);
                obj.setLng(f1);
                obj.setName(s2);
                object.add(obj);
                c++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


class ttt2 extends AsyncTask<Void, String, String> {
    String k, l, m, g, z;
    double c, d;

    public ttt2(double a, double b) {
        c = a;
        d = b;
    }

    @Override
    protected void onPreExecute() {
//        k="https://maps.googleapis.com/maps/api/geocode/json?latlng=22.6891125,75.837065&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        k = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + c + "," + d + "&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(k);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((l = bufferedReader.readLine()) != null) {
                stringBuilder.append(l + "\n");
            }
            Log.e("216", "" + l);
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        m = s;
        super.onPostExecute(s);
        Log.e("143", "" + m);
        meth4(m);
        flag = 1;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    public void meth4(String n) {
        try {
            JSONObject j0 = new JSONObject(n);
            JSONArray j1 = j0.getJSONArray("results");
            JSONObject j2 = j1.getJSONObject(0);
            z = j2.getString("formatted_address");
            MainActivity.st2 = z;
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}