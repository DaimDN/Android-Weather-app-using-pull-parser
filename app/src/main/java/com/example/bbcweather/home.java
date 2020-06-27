package com.example.bbcweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private static final String[] paths = {"Glasgow", "London", "NewYork", "Oman", "Mauritius", "Bangladesh"};
        ArrayList<String> name = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(home.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final TextView dataload = (TextView) findViewById(R.id.tview);
        String data = "";

        switch (position) {
            case 0:
                    data = "Glasgow";

                break;
            case 1:
                data = "London";

                break;
            case 2:
                data = "New York";
                break;

            case 3:
               data = "Oman";
                break;

            case 4:
                data = "Mauritis";
                break;
            case 5:
                data = "Banglandesh";
                break;
            default: data = "Not Available";

        }

        dataload.setText(data);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}