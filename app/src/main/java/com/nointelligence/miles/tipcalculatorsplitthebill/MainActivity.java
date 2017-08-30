package com.nointelligence.miles.tipcalculatorsplitthebill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    EditText edit_bill, edit_percent, edit_number;
    TextView text_billValue, text_tipValue, text_splitValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private boolean allEntered(){
        if(edit_bill.getText().toString().trim().length() == 0){
            return false;
        }
        if(edit_percent.getText().toString().trim().length() == 0){
            return false;
        }
        if(edit_number.getText().toString().trim().length() == 0){
            return false;
        }
        return true;
    }

    private void initUI(){
        text_billValue = (TextView)findViewById(R.id.text_billValue);
        text_tipValue = (TextView)findViewById(R.id.text_tipValue);
        text_splitValue = (TextView)findViewById(R.id.text_splitValue);

        edit_bill = (EditText)findViewById(R.id.edit_bill);
        edit_percent = (EditText)findViewById(R.id.edit_percent);
        edit_number = (EditText)findViewById(R.id.edit_number);

        // When the user exits an EditText without pressing enter
        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(allEntered()){
                    calculateAndDisplay();
                }
            }
        };

        // When the user presses enter in an EditText
        View.OnKeyListener onKeyListener = new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(allEntered()){
                        calculateAndDisplay();
                    }
                    return true;
                }
                return false;
            }
        };

        edit_bill.setOnFocusChangeListener(focusChangeListener);
        edit_percent.setOnFocusChangeListener(focusChangeListener);
        edit_number.setOnFocusChangeListener(focusChangeListener);

        edit_bill.setOnKeyListener(onKeyListener);
        edit_percent.setOnKeyListener(onKeyListener);
        edit_number.setOnKeyListener(onKeyListener);
    }

    private void calculateAndDisplay(){
        double bill = Double.parseDouble(edit_bill.getText().toString());
        double tipPercent = Double.parseDouble(edit_percent.getText().toString()) / 100;
        int num = Integer.parseInt(edit_number.getText().toString());

        // Input verification
        if(num == 0){
            edit_number.setText("1");
            num = 1;
        }

        double billValue = bill * (1.0 + tipPercent);
        double tipValue = (billValue - bill) / num;
        double splitValue = billValue / num;

        text_billValue.setText(String.format("%.2f", billValue));
        text_tipValue.setText(String.format("%.2f", tipValue));
        text_splitValue.setText(String.format("%.2f", splitValue));
    }
}
