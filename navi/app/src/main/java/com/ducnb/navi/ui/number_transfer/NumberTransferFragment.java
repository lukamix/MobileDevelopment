package com.ducnb.navi.ui.number_transfer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ducnb.navi.R;
import com.ducnb.navi.databinding.FragmentNumberTransferBinding;

public class NumberTransferFragment extends Fragment {

    private NumberTransferViewModel slideshowViewModel;
    private FragmentNumberTransferBinding binding;

    private int inputMode = 0;
    private int outputMode = 0;
    private float result = 0;
    EditText input;
    TextView resultText;
    Spinner spinner1,spinner2;
    ArrayAdapter<CharSequence> adapter,adapter2;
    private static int[] convert ={2,8,10,16};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(NumberTransferViewModel.class);

        binding = FragmentNumberTransferBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        Init();
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    private void Init(){
        spinner1 = binding.spinner3;
        spinner2 = binding.spinner4;

        adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.number_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.number_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        input= binding.input2;
        resultText = binding.result2;

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
            resultText.setText(convert(calculateDecValue(inputType,tmp),outputType));
        }
        else {
            resultText.setText("Result");
        }
    }
    private int calculateDecValue(int inputType,String input){
        int res = 0;
        int tmp = 0;
        if(inputType == 0){
            for(int i = input.length()-1;i>=0;i--){
                if(input.charAt(i)=='1') res +=(int) Math.pow(2,tmp);
                tmp++;
            }
        }
        else if (inputType == 3){
            for(int i = input.length()-1;i>=0;i--){
                if(input.charAt(i)=='a'||input.charAt(i)=='A'){
                    res +=10*(int)Math.pow(16,tmp);
                }
                else if(input.charAt(i)=='b'||input.charAt(i)=='B'){
                    res +=11*(int)Math.pow(16,tmp);
                }
                else if(input.charAt(i)=='c'||input.charAt(i)=='C'){
                    res +=12*(int)Math.pow(16,tmp);
                }
                else if(input.charAt(i)=='d'||input.charAt(i)=='D'){
                    res +=13*(int)Math.pow(16,tmp);
                }
                else if(input.charAt(i)=='e'||input.charAt(i)=='E'){
                    res +=14*(int)Math.pow(16,tmp);
                }
                else if(input.charAt(i)=='f'||input.charAt(i)=='F'){
                    res +=15*(int)Math.pow(16,tmp);
                }
                else{
                    res +=Character.getNumericValue(input.charAt(i))*(int)Math.pow(16,tmp);
                }
                tmp++;
            }
        }
        else if(inputType == 1){
            for(int i = input.length()-1;i>=0;i--){
                res +=Character.getNumericValue(input.charAt(i))*(int)Math.pow(8,tmp);
                tmp++;
            }
        }
        else if (inputType ==2){
            res=Integer.valueOf(input);
        }
        return res;
    }
    private String convert(int value,int outputMode){
        return Integer.toString(value,convert[outputMode]);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}