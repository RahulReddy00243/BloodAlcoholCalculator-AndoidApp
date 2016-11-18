package com.example.rnann.bloodalcoholcalculator;

import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText weightET;
    Switch aSwitch;
    String weight = "";
    String gender = "M";
    Button save;
    RadioGroup rg;
    int drinkSize = 1;
    SeekBar sbar;
    TextView alcholPercent;
    int ap=5;
    Button add;
    double BacLevel =0;
    String tempWeight = "";
    TextView statusTV;
    ProgressBar progressBar;
    TextView bacLevelTV;
    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightET = (EditText) findViewById(R.id.WeightET);
        aSwitch = (Switch) findViewById(R.id.Switch);
        rg = (RadioGroup) findViewById(R.id.RadioGroup);
        add = (Button) findViewById(R.id.ButtonAddDrink);
        statusTV = (TextView) findViewById(R.id.StatusTV);
        sbar = (SeekBar) findViewById(R.id.seekBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        bacLevelTV = (TextView) findViewById(R.id.BACLevelTV);
        reset = (Button) findViewById(R.id.ButtonReset);

        sbar.setProgress(5);
        sbar.setMax(25);


        alcholPercent = (TextView) findViewById(R.id.AlcoholPercentTV);
        alcholPercent.setText(5+" %");


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
                    if (Integer.parseInt(weight)==0){
                        Toast.makeText(MainActivity.this,"Please Enter Valid weight",Toast.LENGTH_SHORT).show();
                        weightET.setText("");
                        return;
                    }
                    String tempGender = gender;
                    Log.d("rahul","gender is "+gender+" Weight is"+weight);
                    weightET.setText("");
                    aSwitch.setChecked(true);
                    RadioButton rb = (RadioButton) findViewById(R.id.OneOzRadioButton);
                    rb.setChecked(true);
                    sbar.setProgress(0);
                    bacLevelTV.setText("0.00");
                    progressBar.setProgress(0);
                    statusTV.setText("");
                    statusTV.setBackgroundColor(Color.WHITE);
                    Toast.makeText(MainActivity.this,"Your Weight and Gender is Saved!!",Toast.LENGTH_SHORT).show();
                }
            }
        });




        Log.d("rahul","temp weight is :"+tempWeight);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.OneOzRadioButton:
                        drinkSize = 1;
                        break;
                    case R.id.FiveOzRadioButton:
                        drinkSize = 5;
                        break;
                    case R.id.TwelveOzRadioButton:
                        drinkSize = 12;
                        break;
                }
            }
        });


        sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                Log.d("rahul", "Intial Progress"+progress);
                progress = progress/5;
                Log.d("rahul", " Progress after div"+progress);
                progress = progress*5;
                Log.d("rahul", " Progress after mul"+progress);

                sbar.setProgress(progress);

                alcholPercent.setText(String.valueOf(progress)+" %");

                ap = progress;

                Log.d("rahul","ap value is "+ap);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {




                tempWeight = weight;

                if (tempWeight.equals(null)||tempWeight.equals("")){
                    Toast.makeText(MainActivity.this,"Please Save Your weight and Gender First!!",Toast.LENGTH_SHORT).show();
                    return;
                }

Log.d("rahul","inside add app value is :"+ap);

                double alcopercent = (ap)*0.01;

                Log.d("rahul","alcopercent is :"+alcopercent +"xx"+ap/100);
                double A = alcopercent*drinkSize;

                double numerator = A*6.24;

                Log.d("rahul","numerator value"+numerator);
                double r;

                if (gender=="M"){
                    r = 0.68;
                }else{
                    r = 0.55;
                }



                double denominator = Integer.parseInt(tempWeight)*r;


                Log.d("rahul","denominator value is"+denominator);

                Log.d("rahul","Bac Level is "+BacLevel);

                BacLevel = BacLevel+(numerator/denominator);

                Log.d("rahul", "BAC LEVEL is "+BacLevel);
                progressBar.setMax(25);

                if (BacLevel>0&&BacLevel<=0.08){
                    statusTV.setText("You're Safe");
                    int x = (int) (BacLevel*100);
                    progressBar.setProgress(x);
                    DecimalFormat df = new DecimalFormat("#.##");

                    bacLevelTV.setText(String.valueOf(df.format(BacLevel)));
                    statusTV.setBackgroundColor(Color.GREEN);

                }else if (BacLevel>0.08 && BacLevel<=0.20){
                    statusTV.setText("Be Careful...");
                    int x = (int) (BacLevel*100);
                    progressBar.setProgress(x);
                    bacLevelTV.setText(String.valueOf(BacLevel));
                    DecimalFormat df = new DecimalFormat("#.##");

                    bacLevelTV.setText(String.valueOf(df.format(BacLevel)));

                    statusTV.setBackgroundColor(Color.YELLOW);

                }else if (BacLevel>0.20&& BacLevel< 0.25){
                    statusTV.setText("Over the Limit!");
                    int x = (int) (BacLevel*100);
                    progressBar.setProgress(x);
                    bacLevelTV.setText(String.valueOf(BacLevel));
                    DecimalFormat df = new DecimalFormat("#.##");

                    bacLevelTV.setText(String.valueOf(df.format(BacLevel)));
                    statusTV.setBackgroundColor(Color.RED);



                }else if (BacLevel>=0.25){

                    Toast.makeText(MainActivity.this, "No More Drinks For You!!", Toast.LENGTH_SHORT).show();
                    statusTV.setText("OMG!!!");

                    add.setEnabled(false);
                    save.setEnabled(false);
                    int x = (int) (BacLevel*100);
                    progressBar.setProgress(x);
                    bacLevelTV.setText(String.valueOf(BacLevel));
                    DecimalFormat df = new DecimalFormat("#.##");

                    bacLevelTV.setText(String.valueOf(df.format(BacLevel)));
                    statusTV.setBackgroundColor(Color.GRAY);

                }

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weightET.setText("");
                save.setEnabled(true);
                RadioButton rb = (RadioButton) findViewById(R.id.OneOzRadioButton);
                rb.setChecked(true);
                sbar.setProgress(0);
                add.setEnabled(true);
                bacLevelTV.setText("0.00");
                progressBar.setProgress(0);
                statusTV.setText("");
                statusTV.setBackgroundColor(Color.WHITE);

                weight = "";
                BacLevel=0.00;

            }
        });

    }

}
