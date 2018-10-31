package com.example.jamie.lab3;

import android.app.Activity;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class secAct extends Activity implements View.OnClickListener{
    private boolean canCalc = false;
    String str;
    private String test2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        // Getting Views
        Button button2 = (Button)findViewById(R.id.button2);
        RadioButton buttonusd = (RadioButton)findViewById(R.id.buttonusd);
        RadioButton buttonrand = (RadioButton)findViewById(R.id.buttonrand);
        RadioButton buttonyen = (RadioButton)findViewById(R.id.buttonyen);
        RadioButton buttonschmeckles = (RadioButton)findViewById(R.id.radioButton4);
        //Getting Views

        buttonusd.setOnClickListener(this);
        buttonrand.setOnClickListener(this);
        buttonyen.setOnClickListener(this);
        buttonschmeckles.setOnClickListener(this);
        convert();


    }

    public void onClick(View v){
        //SharedPreferences prefGet = getSharedPreferences("STRING",Activity.MODE_PRIVATE);
        //test2 = prefGet.getString("TEST","Nothing Stored");
        //double nbr= Double.parseDouble(test2);
        Intent intent = getIntent();
        double nbr = intent.getDoubleExtra("CONVERTED",0);
        switch(v.getId()) {

            case (R.id.buttonusd):
                nbr = nbr * 1.2238;
                str = String.valueOf(String.format("%.2f",nbr)+"USD");
                break;


            case (R.id.buttonrand):
                nbr = nbr * 14.86;
                str = String.valueOf(String.format("%.2f",nbr)+"RAND");
                break;

            case (R.id.buttonyen):
                nbr = nbr * 132.54;
                str = String.valueOf(String.format("%.2f",nbr)+"YEN");
                break;

            case (R.id.radioButton4):
                nbr = nbr * 69;
                str = String.valueOf(String.format("%.2f",nbr)+"SCHMEKS");
                break;
        }
    }
    private void convert () {
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != null) {
                    Intent myIntent = new Intent(secAct.this, MainActivity.class);
                    myIntent.putExtra("converted", str);
                    startActivity(myIntent);
                }
            }
        });
    }
}

