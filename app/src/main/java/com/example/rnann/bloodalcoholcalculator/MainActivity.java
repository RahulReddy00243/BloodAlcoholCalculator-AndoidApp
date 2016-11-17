package com.example.rnann.bloodalcoholcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText weightET;
    Switch aSwitch;
    String weight;
    String gender = "M";
    Button save;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightET = (EditText) findViewById(R.id.WeightET);
        aSwitch = (Switch) findViewById(R.id.Switch);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("rahul","Inside listner");

                if (isChecked){
                    Log.d("rahul","Inside is checked condition");
                    gender = "M";
                    aSwitch.setChecked(true);
                }else{
                    gender = "F";
                }
            }
        });



        save = (Button) findViewById(R.id.SaveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weightET.getText().toString().equals(null)||weightET.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter your Weight In lbs!!", Toast.LENGTH_SHORT).show();
                }else{
                    weight = weightET.getText().toString();
                    String tempGender = gender;
                    Log.d("rahul","gender is "+gender+" Weight is"+weight);
                    weightET.setText("");
                    aSwitch.setChecked(true);
                }
            }
        });





    }

}
