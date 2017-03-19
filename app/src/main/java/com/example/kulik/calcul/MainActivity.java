package com.example.kulik.calcul;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView inputField;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, beq, bdiv, bmul, badd, bsub;
    LinearLayout resultsLinearLayout;
    int first_operand;
    boolean exist_first_operand;
    int second_operand;
    boolean exist_second_operand;
    String operation;
    boolean exist_operation;
    String textViewResults;

    private static final String TAG = "log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        beq = (Button) findViewById(R.id.beq);
        bdiv = (Button) findViewById(R.id.bdiv);
        bmul = (Button) findViewById(R.id.bmul);
        badd = (Button) findViewById(R.id.badd);
        bsub = (Button) findViewById(R.id.bsub);
        inputField = (TextView) findViewById(R.id.inputField);
        resultsLinearLayout = (LinearLayout) findViewById(R.id.resultsLinearLayout);

        Button[] buttons = {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, beq, bdiv, bmul, badd, bsub};


        for (Button button : buttons) {
            button.setOnClickListener(this);
        }

        exist_first_operand = false;
        exist_second_operand = false;
        exist_operation = false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//        outState.putStringArrayList();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                resultsLinearLayout.removeAllViewsInLayout();
                Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String previous_content = (String) inputField.getText();
        Log.d(TAG, (String)((Button) findViewById(v.getId())).getText());
        switch (v.getId()){
            case (R.id.beq):
                if (exist_first_operand && exist_second_operand && exist_operation){
                    Pattern pattern = Pattern.compile(" [+] | [-] | [*] | [/] ");
                    String[] numbers = pattern.split(previous_content);
                    first_operand = Integer.parseInt(numbers[0]);
                    second_operand = Integer.parseInt(numbers[1]);
                    String answer;
                    if (Objects.equals(operation, "+")){
                        answer = Integer.toString(first_operand + second_operand);
                    } else if (Objects.equals(operation, "-")){
                        answer = Integer.toString(first_operand - second_operand);
                    } else if (Objects.equals(operation, "*")){
                        answer = Integer.toString(first_operand * second_operand);
                    } else {
                        try {
                            answer = Double.toString(((double)first_operand / second_operand));
                        } catch (Exception e) {
                            answer = e.toString();
                        }
                    }
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(previous_content + " = " + answer);
                    textViewResults = textViewResults + previous_content + " = " + answer + "_";

                    resultsLinearLayout.addView(textView);

                    inputField.setText("");
                    exist_first_operand = false;
                    exist_second_operand = false;
                    exist_operation = false;
                }

                break;

            case (R.id.badd):
                if (!exist_operation && exist_first_operand) {
                    inputField.setText(previous_content + " + " );
                    operation = "+";
                    exist_operation = true;
                }
                break;

            case (R.id.bsub):
                if (!exist_operation && exist_first_operand) {
                    inputField.setText(previous_content + " - ");
                    operation = "-";
                    exist_operation = true;
                }
                break;

            case (R.id.bdiv):
                if (!exist_operation && exist_first_operand) {
                    inputField.setText(previous_content + " / ");
                    operation = "/";
                    exist_operation = true;
                }
                break;

            case (R.id.bmul):
                if (!exist_operation && exist_first_operand) {
                    inputField.setText(previous_content + " * ");
                    operation = "*";
                    exist_operation = true;
                }
                break;

            default:
                exist_first_operand = true;
                if (exist_operation){exist_second_operand = true;}
                String btn_content = (String)((Button) findViewById(v.getId())).getText();
                inputField.setText(previous_content + btn_content);
                break;

        }
    }
}

