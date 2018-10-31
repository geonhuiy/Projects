package com.example.jamie.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String[] newString = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter <String> myArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,newString);
        ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(myArrayAdapter);
        list.setOnItemClickListener(this);
    }
    public void onItemClick (AdapterView parent, android.view.View view, int position, long id){
        TextView text = (TextView)findViewById(R.id.textView2);
        ListView list = (ListView)findViewById(R.id.listView);
        Random myRandom = new Random();
        int rand = myRandom.nextInt(12)+1;
        int selected = position+1;
        if (selected == rand) {
            text.setText("Congrats");
        }
        else {
            text.setText("Try again");
        }
    }
}
