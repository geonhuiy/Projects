package com.example.jamie.lab3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences.Editor;
import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(this);
        TextView text2 = (TextView) findViewById(R.id.textView2);
            Intent intent = getIntent();
            String str = intent.getStringExtra("converted");
            if (str != null) {
                text2.setText(str);
            }
    }

    public void onClick(View v) {
        EditText input = (EditText) findViewById(R.id.edittext);
        String str = input.getText().toString();
        double nbr = Double.parseDouble(str);
        if (str != null && !str.isEmpty()) {
            //SharedPreferences doublePut = getSharedPreferences("STRING",Activity.MODE_PRIVATE);
            //Editor prefEditor = doublePut.edit();
            //prefEditor.putString("TEST",str);
            //prefEditor.commit();
            Intent myIntent = new Intent(this, secAct.class);
            myIntent.putExtra("CONVERTED",nbr);
            startActivity(myIntent);
        }
    }
}
