package com.example.jamie.lab2a;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
public class MainActivity extends Activity {
    Button button1;
    TextView view1,view2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.mybutton);
        view1 = (TextView)findViewById(R.id.textView1);
        view2 = (TextView)findViewById(R.id.text2) ;
        button1.setOnClickListener(myOnClickListener);
    }
    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            view1.setText(R.string.World);
            view2.setText(R.string.Hello);
        }
    };
}
