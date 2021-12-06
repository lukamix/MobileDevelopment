package com.ducnb.navi.ui.unit_transfer;

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
import com.ducnb.navi.databinding.FragmentUnitTransferBinding;

public class UnitTransferFragment extends Fragment {

    private UnitTransferViewModel unitTransferViewModel;
    private FragmentUnitTransferBinding binding;

    private int inputMode = 0;
    private int outputMode = 0;
    private float result = 0;
    EditText input;
    TextView resultText;
    Spinner spinner1,spinner2;
    ArrayAdapter<CharSequence> adapter,adapter2;
    private static float[] convert ={1000,1,0.1f,0.01f,0.001f,0.000001f,0.000000001f,0.0000000001f};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        unitTransferViewModel =
                new ViewModelProvider(this).get(UnitTransferViewModel.class);

        binding = FragmentUnitTransferBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        Init();
        unitTransferViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void Init(){
        spinner1 = binding.spinner;
        spinner2 = binding.spinner2;

        adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        input= binding.input;
        resultText = binding.result;

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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}