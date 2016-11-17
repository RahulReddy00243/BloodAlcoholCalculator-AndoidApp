package com.example.rnann.bloodalcoholcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    EditText weightET;
    Switch aSwitch;
    String weight;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    private void getWandG(){

        weightET = (EditText) findViewById(R.id.WeightET);
        aSwitch = (Switch) findViewById(R.id.Switch);

        weight = weightET.getText().toString();
        

    }
}
