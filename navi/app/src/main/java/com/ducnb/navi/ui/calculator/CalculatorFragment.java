package com.ducnb.navi.ui.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ducnb.navi.databinding.FragmentCalculatorBinding;

import java.text.NumberFormat;
import java.util.Locale;

enum operator{
    DIVIDE,
    MULTIPLY,
    SUBTRACT,
    ADD,
    SQRT,
    POW2,
    REVERSE,
    PERCENT,
    NEGATIVE,
    EQUAL
}
public class CalculatorFragment extends Fragment {

    private final long MAX_VALUE = 10000000000000000l;

    operator inqueue, tempoperator;
    String currentText1 = "";
    String currentText2 = "";
    double result =0d;
    double tempresult = 0d;
    double input= 0d;

    boolean isCommasTyping = false;
    int currentcommas = 0;
    TextView display1;
    TextView display2;
    Button mNumberButton[] = new Button[10];
    Button mOperatorButton[] = new Button[10];
    Button mCEButton,mCButton,mBackButton,mDotButton;

    private CalculatorViewModel calculatorViewModel;
    private FragmentCalculatorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculatorViewModel =
                new ViewModelProvider(this).get(CalculatorViewModel.class);

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        Init();
        calculatorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
    private void Init(){
        display1 = binding.calculation;
        display2 = binding.display;
        //0->9
        mNumberButton[0] = binding.zero;
        mNumberButton[1] = binding.one;
        mNumberButton[2] = binding.two;
        mNumberButton[3] = binding.three;
        mNumberButton[4] = binding.four;
        mNumberButton[5] = binding.five;
        mNumberButton[6] = binding.six;
        mNumberButton[7] = binding.seven;
        mNumberButton[8] = binding.eight;
        mNumberButton[9] = binding.nine;
        mOperatorButton[0] = binding.devide;
        mOperatorButton[1] = binding.multiple;
        mOperatorButton[2] = binding.subtract;
        mOperatorButton[3] = binding.plus;
        mOperatorButton[4] = binding.squareroot;
        mOperatorButton[5] = binding.square;
        mOperatorButton[6] = binding.reverse;
        mOperatorButton[7] = binding.percent;
        mOperatorButton[8] = binding.negative;
        mOperatorButton[9] = binding.equal;
        mCEButton = binding.ce;
        mCButton = binding.c;
        mBackButton = binding.back;
        mDotButton = binding.dot;

        mNumberButton[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickZeroButton();
            }
        });
        mNumberButton[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickOneButton();
            }
        });
        mNumberButton[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickTwoButton();
            }
        });
        mNumberButton[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickThreeButton();
            }
        });
        mNumberButton[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickFourButton();
            }
        });
        mNumberButton[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickFiveButton();
            }
        });
        mNumberButton[6].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickSixButton();
            }
        });
        mNumberButton[7].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickSevenButton();
            }
        });
        mNumberButton[8].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickEightButton();
            }
        });
        mNumberButton[9].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickNineButton();
            }
        });
        //    DIVIDE,
        //    MULTIPLY,
        //    SUBTRACT,
        //    ADD,
        //    SQRT,
        //    POW2,
        //    REVERSE,
        //    PERCENT,
        //    NEGATIVE,
        //    EQUAL
        mOperatorButton[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickDivideButton();
            }
        });
        mOperatorButton[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickMultipleButton();
            }
        });
        mOperatorButton[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickSubtractButton();
            }
        });
        mOperatorButton[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickPlusButton();
            }
        }); mOperatorButton[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickSquareRootButton();
            }
        }); mOperatorButton[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickSquareButton();
            }
        }); mOperatorButton[6].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickReverseButton();
            }
        }); mOperatorButton[7].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickPercentButton();
            }
        }); mOperatorButton[8].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickNegativeButton();
            }
        }); mOperatorButton[9].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickEqualButton();
            }
        });

        //mCEButton,mCButton,mBackButton,mDotButton
        mCEButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickCEButton();
            }
        });
        mCButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickCButton();
            }
        });
        mBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickBackButton();
            }
        });
        mDotButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickDotButton();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void insertNumber(int number){
        if(!isCommasTyping){
            if(tempresult * 10 + number < MAX_VALUE){
                tempresult = tempresult == 0 ? number : tempresult * 10 + number;
            }
            currentText2 = String.valueOf((long)(tempresult));
        }
        else {
            currentcommas++;
            tempresult = tempresult + number/(double)(Math.pow(10,currentcommas));
            System.out.println(number/(double)(10*currentcommas));
            currentText2 = String.valueOf(tempresult);
        }

        if(currentText2.length()<14){
            display2.setTextSize(45);
        }
        else if(currentText2.length()>=14 && currentText2.length()<18){
            display2.setTextSize(36);
        }
        else if (currentText2.length()>=18){
            display2.setTextSize(32);
        }
        display2.setText(currentText2);
        tempoperator = null;
    }
    private void insertOperator(operator o){
        if(inqueue == null){
            inqueue = o;
            result = tempresult;
        }
        else{
            inqueue = o;
            calculateCurrent();
        }
        tempresult=0;
        String symbol;
        symbol = o == operator.ADD ? " +":
                o == operator.DIVIDE ? " ÷":
                        o ==operator.MULTIPLY ? " ×":
                                o == operator.SUBTRACT ? " -":
                                        "";
        currentText1 = NumberFormat.getNumberInstance(Locale.US).format(result)+symbol;
        currentText2 = NumberFormat.getNumberInstance(Locale.US).format(tempresult);
        display1.setText(currentText1);
        display2.setText(currentText2);
        tempoperator = null;
    }
    private void insertSpecialOperator(operator o){
        if(tempoperator != operator.EQUAL){
            if(inqueue==null){
                if(o==operator.REVERSE){
                    currentText1 = "1/("+Double.toString(tempresult)+")";
                }
                else if(o==operator.POW2){
                    currentText1 = "sqr("+Double.toString(tempresult)+")";
                }
                else if(o==operator.SQRT){
                    currentText1 = "sqrt("+Double.toString(tempresult)+")";
                }
                else if(o==operator.PERCENT){
                    currentText1 = "("+Double.toString(tempresult)+")%";
                }
                else if(o==operator.NEGATIVE){
                    currentText1 = "- "+Double.toString(tempresult);
                }
            }
            else{
                currentText1 = Double.toString(result);
                currentText1 += inqueue == operator.ADD ? " + ":
                        inqueue == operator.SUBTRACT ? " - ":
                                inqueue == operator.DIVIDE ? " ÷ ":
                                        inqueue == operator.MULTIPLY ? " × ":
                                                "";
                if(o==operator.REVERSE){
                    currentText1 += "1/("+Double.toString(tempresult)+")";
                }
                else if(o==operator.POW2){
                    currentText1 += "sqr("+Double.toString(tempresult)+")";
                }
                else if(o==operator.SQRT){
                    currentText1 += "sqrt("+Double.toString(tempresult)+")";
                }
                else if(o==operator.PERCENT){
                    currentText1 += "("+Double.toString(tempresult)+")%";
                }
                else if(o==operator.NEGATIVE){
                    currentText1 += "- "+Double.toString(tempresult);
                }
            }
            display1.setText(currentText1);
            if(o==operator.REVERSE){
                tempresult = 1/tempresult;
            }
            else if(o==operator.POW2){
                tempresult = Math.pow(tempresult,2);
            }
            else if(o==operator.SQRT){
                tempresult = Math.sqrt(tempresult);
            }
            else if(o==operator.PERCENT){
                tempresult = tempresult/100;
            }
            else if(o==operator.NEGATIVE){
                tempresult = - tempresult;
            }
            currentText2 = String.valueOf(tempresult);
            display2.setText(currentText2);
        }
    }
    private void calculateCurrent(){
        result = inqueue == operator.DIVIDE ? result/tempresult :
                inqueue == operator.MULTIPLY ? result * tempresult :
                        inqueue == operator.ADD ? result + tempresult :
                                inqueue == operator.SUBTRACT ? result - tempresult :
                                        inqueue == operator.NEGATIVE ? result = -result : result;
    }
    public void onClickMCButton() {

    }

    public void onClickMRButton() {
    }

    public void onClickMPlusButton() {
    }

    public void onClickMSubtractButton() {
    }

    public void onClickMSButton() {
    }

    public void onClickMButton() {
    }

    public void onClickPercentButton() {
        insertSpecialOperator(operator.PERCENT);
    }

    public void onClickCEButton() {
        tempresult =0;
        isCommasTyping=false;
        currentText2 = NumberFormat.getNumberInstance(Locale.US).format(tempresult);
        display2.setText(currentText2);
    }

    public void onClickCButton() {
        tempresult=0;
        result =0;
        currentText1 ="";
        isCommasTyping=false;
        currentText2 = NumberFormat.getNumberInstance(Locale.US).format(tempresult);
        display1.setText(currentText1);
        display2.setText(currentText2);
    }

    public void onClickBackButton() {
        if(!isCommasTyping){
            if(tempoperator == operator.EQUAL){
                currentText1 ="";
                display1.setText(currentText1);
            }
            else{
                long temp = (long)(tempresult/10);
                tempresult =temp;
                currentText2 = NumberFormat.getNumberInstance(Locale.US).format(tempresult);
                display2.setText(currentText2);
            }
        }
        else{
            Character c = currentText2.charAt(currentText2.length()-1);
            if(c!='.'){
                int tempUnit = Integer.valueOf(c);
                tempresult -= tempUnit/(10*currentcommas);
                currentcommas--;
            }
            else{
                isCommasTyping =false;
            }
            currentText2 = currentText2.substring(0,currentText2.length()-2);
            display2.setText(currentText2);
        }
    }

    public void onClickReverseButton() {
        insertSpecialOperator(operator.REVERSE);
    }

    public void onClickSquareButton() {
        insertSpecialOperator(operator.POW2);
    }

    public void onClickSquareRootButton() {
        insertSpecialOperator(operator.SQRT);
    }

    public void onClickDivideButton() {
        insertOperator(operator.DIVIDE);
    }
    public void onClickSevenButton() {
        insertNumber(7);
    }

    public void onClickEightButton() {
        insertNumber(8);
    }

    public void onClickNineButton() {
        insertNumber(9);
    }

    public void onClickMultipleButton() {
        insertOperator(operator.MULTIPLY);
    }

    public void onClickFourButton() {
        insertNumber(4);
    }

    public void onClickFiveButton() {
        insertNumber(5);
    }

    public void onClickSixButton() {
        insertNumber(6);
    }

    public void onClickSubtractButton() {
        insertOperator(operator.SUBTRACT);
    }

    public void onClickOneButton() {
        insertNumber(1);
    }

    public void onClickTwoButton() {
        insertNumber(2);
    }

    public void onClickThreeButton() {
        insertNumber(3);
    }

    public void onClickPlusButton() {
        insertOperator(operator.ADD);
    }

    public void onClickNegativeButton() {
        insertOperator(operator.NEGATIVE);
    }

    public void onClickZeroButton() {
        insertNumber(0);
    }

    public void onClickDotButton() {
        if(!isCommasTyping){
            isCommasTyping = true;
            currentText2+=".";
            display2.setText(currentText2);
        }
    }

    public void onClickEqualButton() {
        if(inqueue == null){
            result = tempresult;
            currentText1 = Double.toString(result);
            currentText2 = Double.toString(tempresult);
        }
        else{
            currentText1 = Double.toString(result);
            currentText1 += (inqueue==operator.ADD) ? " + " :
                    inqueue == operator.DIVIDE ? " ÷ ":
                            inqueue == operator.SUBTRACT ? " - ":
                                    inqueue == operator.MULTIPLY ? " × ":"";
            currentText1 += Double.toString(tempresult) +" = ";
            calculateCurrent();
            tempresult=result;
            currentText2 = Double.toString(result);
            display1.setText(currentText1);
            display2.setText(currentText2);
            inqueue = null;
            tempoperator = operator.EQUAL;
            isCommasTyping = false;
        }
    }
}