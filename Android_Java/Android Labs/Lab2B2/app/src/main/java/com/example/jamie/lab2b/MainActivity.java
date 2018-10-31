package com.example.jamie.lab2b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private double val1 = Double.NaN;
    private double val2;
    private static final char Addition = '+';
    private static final char Substraction = '-';
    private static final char Multiplication = '*';
    private static final char Division = '/';
    private char Current_Action;
    DecimalFormat decimalFormat = new DecimalFormat("#.######");
    private boolean duplicate = false;
    TextView view1;
    Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, multiply, divide, c, eqauls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetViews();
        SetOnClickListeners();
    }


    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.one):
                    view1.setText(view1.getText().toString() + getString(R.string.button1));
                    duplicate = false;
                    break;
                case (R.id.two):
                    view1.setText(view1.getText().toString() + "2");
                    duplicate = false;
                    break;
                case (R.id.three):
                    view1.setText(view1.getText().toString() + "3");
                    duplicate = false;
                    break;
                case (R.id.four):
                    view1.setText(view1.getText().toString() + "4");
                    duplicate = false;
                    break;
                case (R.id.five):
                    view1.setText(view1.getText().toString() + "5");
                    duplicate = false;
                    break;
                case (R.id.six):
                    view1.setText(view1.getText().toString() + "6");
                    duplicate = false;
                    break;
                case (R.id.seven):
                    view1.setText(view1.getText().toString() + "7");
                    duplicate = false;
                    break;
                case (R.id.eight):
                    view1.setText(view1.getText().toString() + "8");
                    duplicate = false;
                    break;
                case (R.id.nine):
                    view1.setText(view1.getText().toString() + "9");
                    duplicate = false;
                    break;
                case (R.id.zero):
                    view1.setText(view1.getText().toString() + "0");
                    duplicate = false;
                    break;
                case (R.id.plus):
                    if (duplicate == false) {
                        Calculate();
                        Current_Action = Addition;
                        view1.setText(String.valueOf(val1)+'+');
                        duplicate = true;
                        break;
                    }
                    else {
                        // do nothing
                        break;
                    }
                case (R.id.minus):
                    if (duplicate == false) {
                        Calculate();
                        Current_Action = Substraction;
                        view1.setText(String.valueOf(val1)+'-');
                        duplicate = true;
                        break;
                    }
                    else {
                        // do nothing
                        break;
                    }
                case (R.id.divide):
                    if (duplicate == false) {
                        Calculate();
                        Current_Action = Division;
                        view1.setText(String.valueOf(val1)+'/');
                        duplicate = true;
                        break;
                    }
                    else {
                        // do nothing
                        break;
                    }
                case (R.id.mult):
                    if (duplicate == false) {
                        Current_Action = Multiplication;
                        Calculate();
                        view1.setText(String.valueOf(val1)+'*');
                        duplicate = true;
                        break;
                    }
                    else {
                        // do nothing
                        break;
                    }
                case (R.id.C):
                    view1.setText(null);
                    break;
                case (R.id.equals):
                    Calculate();
                    view1.setText(view1.getText().toString() + String.valueOf(val2));
                    val1=Double.NaN;
                    Current_Action = '0';
                    break;
            }
        }
    };
    //Other Methods
        private void GetViews() {
            view1 = (TextView) findViewById(R.id.text1);
            one = (Button) findViewById(R.id.one);
            two = (Button) findViewById(R.id.two);
            three = (Button) findViewById(R.id.three);
            four = (Button) findViewById(R.id.four);
            five = (Button) findViewById(R.id.five);
            six = (Button) findViewById(R.id.six);
            seven = (Button) findViewById(R.id.seven);
            eight = (Button) findViewById(R.id.eight);
            nine = (Button) findViewById(R.id.nine);
            zero = (Button) findViewById(R.id.zero);
            plus = (Button) findViewById(R.id.plus);
            minus = (Button) findViewById(R.id.minus);
            multiply = (Button) findViewById(R.id.mult);
            c = (Button) findViewById(R.id.C);
            eqauls = (Button) findViewById(R.id.equals);
            divide = (Button) findViewById(R.id.divide);
        }
        private void SetOnClickListeners() {
            one.setOnClickListener(myOnClickListener);
            one.setOnClickListener(myOnClickListener);
            two.setOnClickListener(myOnClickListener);
            three.setOnClickListener(myOnClickListener);
            four.setOnClickListener(myOnClickListener);
            five.setOnClickListener(myOnClickListener);
            six.setOnClickListener(myOnClickListener);
            seven.setOnClickListener(myOnClickListener);
            eight.setOnClickListener(myOnClickListener);
            nine.setOnClickListener(myOnClickListener);
            zero.setOnClickListener(myOnClickListener);
            plus.setOnClickListener(myOnClickListener);
            minus.setOnClickListener(myOnClickListener);
            multiply.setOnClickListener(myOnClickListener);
            divide.setOnClickListener(myOnClickListener);
            c.setOnClickListener(myOnClickListener);
        }
        private void Calculate() {
            if (!Double.isNaN(val1)) {
                val2 = Double.parseDouble(view1.getText().toString());
                if (Current_Action == Addition) {
                    val1 = this.val1+val2;
                }
                else if (Current_Action == Substraction) {
                    val1 = this.val1+val2;
                }
                else if (Current_Action == Multiplication) {
                    val1 = this.val1+val2;
                }
                else if (Current_Action == Division) {
                    val1 = this.val1/val2;
                }
            }
            else {
                val1 = Double.parseDouble(view1.getText().toString());
            }
        }
    //Other Methods
}
