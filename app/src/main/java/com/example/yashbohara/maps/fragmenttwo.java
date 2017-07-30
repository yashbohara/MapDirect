package com.example.yashbohara.maps;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class fragmenttwo extends Fragment {
    EditText e3,e4;
    Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.placesearch,container,false);
        e3 = (EditText) view.findViewById(R.id.editText3);
        e4 = (EditText) view.findViewById(R.id.editText4);
        button=(Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e3.getText().toString().length()==0){
                    e3.setHint("Search Text Required");
                }
                else if(e4.getText().toString().length()==0){
                    e4.setHint("City Field Required");
                }
                else{
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=an+"+e3.getText().toString()+"+"+e4.getText().toString()));
                startActivityForResult(intent,0);
            }}
        });
        return view;
    }
}
