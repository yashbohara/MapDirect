package com.example.yashbohara.maps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.yashbohara.maps.CustomAdapter.cc;
import static com.example.yashbohara.maps.Main2Activity.a;
import static com.example.yashbohara.maps.Main2Activity.b;
import static com.example.yashbohara.maps.Main2Activity.c;
import static com.example.yashbohara.maps.MainActivity.arr1;
import static com.example.yashbohara.maps.MainActivity.arr3;
import static com.example.yashbohara.maps.MainActivity.arr4;
import static com.example.yashbohara.maps.MainActivity.progressDialog;
import static com.example.yashbohara.maps.MainActivity.s2;

public class CustomAdapter extends ArrayAdapter {
    gs obj;
    static Context cc;

    public CustomAdapter(Context context, int resource, Context cont) {
        super(context, R.layout.row);
        cc = cont;
    }

    List list = new ArrayList();

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        view = convertView;
        LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.row, parent, false);
        final TextView t1 = (TextView) view.findViewById(R.id.textView);
        final TextView t2 = (TextView) view.findViewById(R.id.textView2);
        final TextView t3 = (TextView) view.findViewById(R.id.textView3);
        Button b1 = (Button) view.findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Float.parseFloat(t1.getText().toString());
                b = Float.parseFloat(t2.getText().toString());
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                new ttt(a, b).execute();/*
                Intent intent=new Intent(getContext(),MapsActivity.class);
                startActivity(getContext(),intent, Bundle.EMPTY);*/
            }
        });
        Log.e("gggggggggggggg", "7");
        gs obj = (gs) getItem(position);
        t1.setText("" + obj.getLat());
        t2.setText("" + obj.getLng());
        t3.setText("" + obj.getName());
        Log.e("list", "" + list);
        return view;
    }

    public void add(gs object) {
        Log.e("gggggggggggggg", "6");
        list.add(object);

    }
}

class ttt extends AsyncTask<Void, String, String> {
    String k, l, m, g, z;
    double c, d;

    //static int flag=0;
    public ttt(double a, double b) {
        c = a;
        d = b;
    }

    @Override
    protected void onPreExecute() {
//        k="https://maps.googleapis.com/maps/api/geocode/json?latlng=22.6891125,75.837065&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        k = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + c + "," + d + "&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        Log.e("kkkkkkkkkkkkkkkkkkkkk", "" + k);
        super.onPreExecute();
    }

    //https://maps.googleapis.com/maps/api/geocode/json?latlng=22.6823665,75.8339696&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4
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
        new ggg().execute();
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
            g = j2.getString("formatted_address");
            MainActivity.st = g;
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

class ggg extends AsyncTask<Void, String, String> {
    String j, k, l;

    @Override
    protected void onPreExecute() {
        String ss = MainActivity.st;
        String ss2 = MainActivity.st2;
        ss = ss.replace(" ", "+");
        ss2 = ss2.replace(" ", "+");
        //j="https://maps.googleapis.com/maps/api/directions/json?origin=Mayank+Hospital+indore&destination="+ss+"&key=AIzaSyCNxz4DlZJH_ieQgDtDL49heZZr03Sfh7M";
        j = "https://maps.googleapis.com/maps/api/directions/json?origin=" + ss2 + "&destination=" + ss + "&mode=walking&avoid=highways&key=AIzaSyCNxz4DlZJH_ieQgDtDL49heZZr03Sfh7M";
        //j="https://maps.googleapis.com/maps/api/directions/json?origin=Royal+heights+manormaganj&destination=730,%20Annapurna%20Rd,%20Usha%20Nagar,%20Sudama%20Nagar,%20Indore,%20Madhya%20Pradesh%20452009,%20India&key=AIzaSyCNxz4DlZJH_ieQgDtDL49heZZr03Sfh7M";
        //j="https://maps.googleapis.com/maps/api/directions/json?origin=Royal+heights+manormaganj&destination=730,Annapurna Rd,Usha Nagar,Sudama Nagar,Indore,Madhya Pradesh 452009,India&key=AIzaSyCNxz4DlZJH_ieQgDtDL49heZZr03Sfh7M";
        //j = "https://maps.googleapis.com/maps/api/directions/json?origin="+e3.getText()+"&destination="+e4.getText()+"&key=AIzaSyCNxz4DlZJH_ieQgDtDL49heZZr03Sfh7M";
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        k = s;
        Log.e("195", "" + k);
        super.onPostExecute(s);
        meth1(s);
        new jjj().execute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url2 = new URL(j);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void meth1(String v) {
        try {
            JSONObject g1 = new JSONObject(v);
            JSONArray n1 = g1.getJSONArray("routes");
            JSONObject g2 = n1.getJSONObject(0);
            JSONArray n2 = g2.getJSONArray("legs");
            JSONObject g3 = n2.getJSONObject(0);
            JSONObject g4 = g3.getJSONObject("end_location");
            double end = g4.getDouble("lat");
            double end2 = g4.getDouble("lng");
            JSONArray n3 = g3.getJSONArray("steps");
            arr1 = new double[n3.length() + 1];
            MainActivity.arr2 = new double[n3.length() + 1];

            int count = 0;
            while (count < n3.length()) {
                Log.e("count", "" + n3.length());
                JSONObject g5 = n3.getJSONObject(count);
                JSONObject g6 = g5.getJSONObject("start_location");
                arr1[count] = g6.getDouble("lat");
                MainActivity.arr2[count] = g6.getDouble("lng");
                count++;
            }
            arr1[count] = end;
            MainActivity.arr2[count] = end2;

            s2 = "";
            //afterwards
            for (int i = 0; i < arr1.length; i++) {
                if (i < arr1.length - 1)
                    s2 += arr1[i] + "," + MainActivity.arr2[i] + "|";
                else {
                    s2 += arr1[i] + "," + MainActivity.arr2[i];
                }
            }
            Log.e("string :", s2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}


//afterwards
class jjj extends AsyncTask<Void, String, String> {
    String t, u, v;

    @Override
    protected void onPreExecute() {
        //t="https://roads.googleapis.com/v1/snapToRoads?path=22.6977386,75.8824149|22.698838,75.8780404|22.7036063,75.8734144|22.6979909,75.86480329999999|22.6963917,75.8656436&interpolate=true&key=AIzaSyCrFZ27u3UpRJi7LhPXtGN4E-Uk6tXdKnc";
        //t="https://roads.googleapis.com/v1/snapToRoads?path=22.6977386,75.8824149|22.698838,75.8780404|22.7036063,75.8734144|22.6979909,75.86480329999999&interpolate=true&key=AIzaSyCrFZ27u3UpRJi7LhPXtGN4E-Uk6tXdKnc";
        //t="https://roads.googleapis.com/v1/snapToRoads?path=22.6974531,75.87670899999999|22.6924933,75.86754669999999&interpolate=true&key=AIzaSyCrFZ27u3UpRJi7LhPXtGN4E-Uk6tXdKnc";
        //t="https://roads.googleapis.com/v1/snapToRoads?path=22.7230103,75.8864038|22.7184425,75.8843931|22.7253048,75.88714349999999|22.725479,75.89229159999999|22.7257016,75.8922659&interpolate=true&key=AIzaSyCrFZ27u3UpRJi7LhPXtGN4E-Uk6tXdKnc";
        t = "https://roads.googleapis.com/v1/snapToRoads?path=" + s2 + "&interpolate=true&key=AIzaSyCrFZ27u3UpRJi7LhPXtGN4E-Uk6tXdKnc";
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        u = s;
        Log.e("195", "" + u);
        Log.e("195", "" + t);
        super.onPostExecute(s);
        meth2(u);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url2 = new URL(t);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((v = bufferedReader.readLine()) != null) {
                stringBuilder.append(v + "\n");
            }
            Log.e("216", "" + v);
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void meth2(String h) {
        try {
            JSONObject h1 = new JSONObject(h);
            JSONArray b1 = h1.getJSONArray("snappedPoints");
            int count3 = 0;
            MainActivity.arr3 = new double[b1.length()];
            MainActivity.arr4 = new double[b1.length()];
            while (count3 < b1.length()) {
                JSONObject h2 = b1.getJSONObject(count3);
                JSONObject h3 = h2.getJSONObject("location");
                arr3[count3] = h3.getDouble("latitude");
                arr4[count3] = h3.getDouble("longitude");
                count3++;
            }
            progressDialog.dismiss();
            Intent i = new Intent(cc, MapsActivity.class);
            c.startActivity(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}