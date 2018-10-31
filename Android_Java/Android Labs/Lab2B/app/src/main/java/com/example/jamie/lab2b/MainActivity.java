package com.example.jamie.lab2b;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity
{

    TextView result, numberView;
    Button equals, subtract, multiply, clear, sum, divide;
    Button zero, one, two, three, four, five, six, seven, eight, nine;
    Vibrator vibrator;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char EQUAL = '0';
    private double val1 = Double.NaN;
    private double val2;
    private char ACTION;
    private boolean numberPressed = false;
    private boolean endOfCalculation = false;
    private boolean noNewNumber = false;
    private boolean repeatSymbol = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIView();
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        zero.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "0");
                numberBooleans();
                buttons();
            }
        });
        one.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "1");
                numberBooleans();
                buttons();
            }
        });
        two.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "2");
                numberBooleans();
                buttons();
            }
        });
        three.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "3");
                numberBooleans();
                buttons();
            }
        });
        four.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "4");
                numberBooleans();
                buttons();
            }
        });
        five.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "5");
                numberBooleans();
                buttons();
            }
        });
        six.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "6");
                numberBooleans();
                buttons();
            }
        });
        seven.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "7");
                numberBooleans();
                buttons();
            }
        });
        eight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "8");
                numberBooleans();
                buttons();
            }
        });
        nine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberView.setText(numberView.getText().toString() + "9");
                numberBooleans();
                buttons();
            }
        });
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                if (endOfCalculation == true || noNewNumber == true || repeatSymbol == true)
                {
                    clear();
                }
                else if (endOfCalculation == false && noNewNumber == false && numberPressed == false)
                {
                    clear();
                }
                else
                {
                    compute();
                    ACTION = ADDITION;
                    result.setText(String.valueOf(val1) + "+");
                    numberView.setText(null);
                    repeatSymbol = true;

                }
                buttons();


            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                if (endOfCalculation == true || noNewNumber == true || repeatSymbol == true)
                {
                    clear();
                }
                else if (endOfCalculation == false && noNewNumber == false && numberPressed == false)
                {
                    clear();
                }
                else
                {
                    compute();
                    ACTION = SUBTRACTION;
                    result.setText(String.valueOf(val1) + "-");
                    numberView.setText(null);
                    repeatSymbol = true;

                }
                buttons();
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (endOfCalculation == true || noNewNumber == true || repeatSymbol == true)
                {
                    clear();
                }
                else if (endOfCalculation == false && noNewNumber == false && numberPressed == false)
                {
                    clear();
                }
                else
                {
                    compute();
                    ACTION = MULTIPLICATION;
                    result.setText(String.valueOf(val1) + "*");
                    numberView.setText(null);
                    repeatSymbol = true;

                }
                buttons();

            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                if (endOfCalculation == true || noNewNumber == true || repeatSymbol == true)
                {
                    clear();
                }
                else if (endOfCalculation == false && noNewNumber == false && numberPressed == false)
                {
                    clear();
                }

                else
                {
                    compute();
                    ACTION = DIVISION;
                    result.setText(String.valueOf(val1) + "/");
                    numberView.setText(null);
                    repeatSymbol = true;

                }
                buttons();
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                if (endOfCalculation == true || noNewNumber == true)
                {
                    clear();
                }
                else if (endOfCalculation == false && noNewNumber == false && numberPressed == false)
                {
                    clear();
                }
                else if (repeatSymbol == true && noNewNumber == false && numberPressed == true)
                {
                    clear();
                }
                else
                {
                    compute();
                    ACTION = EQUAL;
                    result.setText(result.getText().toString() + String.valueOf(val2));
                    numberView.setText('=' + String.valueOf(val1));
                    numberPressed = true;
                    endOfCalculation = true;
                    repeatSymbol = false;



                }
                buttons();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clears all
                clear();
                buttons();
               /*
               //Backspace code
                if(numberView.getText().length() > 0)
                {
                    CharSequence name = numberView.getText().toString();
                    numberView.setText(name.subSequence(0, name.length()-1));
                }
                else
                {
                    val1 = Double.NaN;
                    val2 = Double.NaN;
                    numberView.setText(null);
                    result.setText(null);
                }
                */
            }
        });

    }


    private void buttons ()
    {
        vibrator.vibrate(50);
    }
    private void numberBooleans()
    {
        numberPressed = true;
        noNewNumber = false;
        repeatSymbol = false;

    }


    private void  clear()
    {
        val1 = Double.NaN;
        val2 = Double.NaN;
        numberView.setText(null);
        result.setText(null);
        numberPressed = false;
        endOfCalculation = false;
        noNewNumber = false;
        repeatSymbol = false;
    }

    private void setupUIView()
    {
        result = (TextView) findViewById(R.id.result);
        numberView = (TextView) findViewById(R.id.numberView);
        equals = (Button) findViewById(R.id.equals);
        subtract = (Button) findViewById(R.id.subtract);
        multiply = (Button) findViewById(R.id.multiply);
        clear = (Button) findViewById(R.id.clear);
        sum = (Button) findViewById(R.id.sum);
        divide = (Button) findViewById(R.id.division);
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
    }


    private void compute()
    {
        if(!Double.isNaN(val1))

        {
            val2 = Double.parseDouble(numberView.getText().toString());

            switch (ACTION)
            {
                case ADDITION:
                    val1 = val1 + val2;
                    break;

                case SUBTRACTION:
                    val1 = val1 - val2;
                    break;

                case MULTIPLICATION:
                    val1 = val1 * val2;
                    break;

                case DIVISION:
                    val1 = val1 / val2;
                    break;

                case EQUAL:
                    break;
            }
        }
        else if (Double.isNaN(val1) && numberPressed == false && endOfCalculation == false)
        {
            clear();
        }
        else
            {
                val1 = Double.parseDouble(numberView.getText().toString());
            }
    }

}
