package com.ducnb.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigInteger;
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
public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display1 = findViewById(R.id.calculation);
        display2 = findViewById(R.id.display);
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
    public void onClickMCButton(View view) {

    }

    public void onClickMRButton(View view) {
    }

    public void onClickMPlusButton(View view) {
    }

    public void onClickMSubtractButton(View view) {
    }

    public void onClickMSButton(View view) {
    }

    public void onClickMButton(View view) {
    }

    public void onClickPercentButton(View view) {
        insertSpecialOperator(operator.PERCENT);
    }

    public void onClickCEButton(View view) {
        tempresult =0;
        isCommasTyping=false;
        currentText2 = NumberFormat.getNumberInstance(Locale.US).format(tempresult);
        display2.setText(currentText2);
    }

    public void onClickCButton(View view) {
        tempresult=0;
        result =0;
        currentText1 ="";
        isCommasTyping=false;
        currentText2 = NumberFormat.getNumberInstance(Locale.US).format(tempresult);
        display1.setText(currentText1);
        display2.setText(currentText2);
    }

    public void onClickBackButton(View view) {
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

    public void onClickReverseButton(View view) {
        insertSpecialOperator(operator.REVERSE);
    }

    public void onClickSquareButton(View view) {
        insertSpecialOperator(operator.POW2);
    }

    public void onClickSquareRootButton(View view) {
        insertSpecialOperator(operator.SQRT);
    }

    public void onClickDivideButton(View view) {
        insertOperator(operator.DIVIDE);
    }
    public void onClickSevenButton(View view) {
        insertNumber(7);
    }

    public void onClickEightButton(View view) {
        insertNumber(8);
    }

    public void onClickNineButton(View view) {
        insertNumber(9);
    }

    public void onClickMultipleButton(View view) {
        insertOperator(operator.MULTIPLY);
    }

    public void onClickFourButton(View view) {
        insertNumber(4);
    }

    public void onClickFiveButton(View view) {
        insertNumber(5);
    }

    public void onClickSixButton(View view) {
        insertNumber(6);
    }

    public void onClickSubtractButton(View view) {
        insertOperator(operator.SUBTRACT);
    }

    public void onClickOneButton(View view) {
        insertNumber(1);
    }

    public void onClickTwoButton(View view) {
        insertNumber(2);
    }

    public void onClickThreeButton(View view) {
        insertNumber(3);
    }

    public void onClickPlusButton(View view) {
        insertOperator(operator.ADD);
    }

    public void onClickNegativeButton(View view) {
        insertOperator(operator.NEGATIVE);
    }

    public void onClickZeroButton(View view) {
        insertNumber(0);
    }

    public void onClickDotButton(View view) {
        if(!isCommasTyping){
            isCommasTyping = true;
            currentText2+=".";
            display2.setText(currentText2);
        }
    }

    public void onClickEqualButton(View view) {
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