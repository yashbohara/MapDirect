package com.example.yashbohara.maps;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.yashbohara.maps.Fragone.progressDialog;
import static com.example.yashbohara.maps.Fragone.select;
import static com.example.yashbohara.maps.MainActivity.lat1;
import static com.example.yashbohara.maps.MainActivity.lng1;
import static com.example.yashbohara.maps.MainActivity.pq;

public class Fragone extends Fragment {
    Button nearsearch;
    static ProgressDialog progressDialog;
    static Spinner select;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nearby, container, false);
        nearsearch = (Button) view.findViewById(R.id.b2);
        select = (Spinner) view.findViewById(R.id.spinner);
        nearsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //taking lat and lng as input and returning the nearby search according to type
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                Log.e("112121", "11");
                new abc(getActivity()).execute();
                progressDialog.show();
                while (progressDialog.equals(true)) {
                    Log.e("progress", "successful");
                }
            }
        });
        return view;
    }
}

class abc extends AsyncTask<Void, String, String> {
    String a, b, c;
    String d;
    Context context1;

    public abc(Context ccc) {
        context1 = ccc;
    }

    @Override
    protected void onPreExecute() {
        d = select.getSelectedItem().toString();
        //b="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        //b = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + MainActivity.e1.getText() + "," + MainActivity.e2.getText() + "&radius=5000&type=" + d + "&sensor=false&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        b = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lat1 + "," + lng1 + "&radius=500&type=" + d + "&sensor=false&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        //b="https://maps.googleapis.com/maps/api/geocode/json?address=indore+"+MainActivity.e3.getText()+"+"+MainActivity.e4.getText()+"&Country:India&sensor=false&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        //b="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+MainActivity.e2.getText()+","+MainActivity.e1.getText()+"&radius=500000&type=bank&sensor=false&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";
        //b="https://maps.googleapis.com/maps/api/place/search/json?location=22.7196,75.8577&radius=5000&type=bank&sensor=false&key=AIzaSyBP4SVZQtOEEKRTmUuNlyScCZ3RR-5BQE4";

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        a = s;
        pq = a;
        super.onPostExecute(s);
        progressDialog.dismiss();
        Log.e("pq", "" + pq);
        Log.e("a", "" + s);
        Intent intent = new Intent(context1, Main2Activity.class);
        intent.putExtra("l1", lat1);
        intent.putExtra("l2", lng1);
        intent.putExtra("js", pq);
        intent.putExtra("spinner", select.getSelectedItem().toString());
        Log.e("intent pq", "" + pq);
        context1.startActivity(intent);
        Log.e("sssssssssss", "ssss22");
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(b);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((c = bufferedReader.readLine()) != null) {
                stringBuilder.append(c + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            //progressDialog.dismiss();
            return stringBuilder.toString().trim();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}