package com.ducnb.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private int inputMode = 0;
    private int outputMode = 0;
    private float result = 0;
    EditText input;
    TextView resultText;
    Spinner spinner1,spinner2;
    ArrayAdapter<CharSequence> adapter,adapter2;
    private static float[] convert ={1000,1,0.1f,0.01f,0.001f,0.000001f,0.000000001f,0.0000000001f};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner2 =(Spinner) findViewById(R.id.spinner2);
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        input = findViewById(R.id.input);
        resultText = findViewById(R.id.result);
        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                calculate(inputMode,outputMode);
            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                inputMode = position;
                calculate(inputMode,outputMode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                inputMode = 0;
            }

        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                outputMode = position;
                calculate(inputMode,outputMode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                outputMode = 0;
            }

        });
    }
    private void calculate(int inputType,int outputType){
        String tmp = input.getText().toString();
        if(tmp.length()>0){
            int inttmp = Integer.parseInt(tmp);
            result = inttmp * (convert[inputType]/convert[outputType]);
            resultText.setText(String.valueOf(result));
        }
        else {
            resultText.setText("Result");
        }
    }
}