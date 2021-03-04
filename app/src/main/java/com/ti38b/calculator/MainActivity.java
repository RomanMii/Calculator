package com.ti38b.calculator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextView inputText;
    TextView outputText;
    androidx.gridlayout.widget.GridLayout gridLayout;
    View additionalButtons,sideButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = getLayoutInflater();
        inputText = findViewById(R.id.input);
        outputText = findViewById(R.id.output);

        gridLayout = findViewById(R.id.buttonsID);
        additionalButtons = inflater.inflate(R.layout.additional_buttons,null);
        sideButtons = inflater.inflate(R.layout.side_buttons,null);
    }

    public void onClick_buttonEqual(View view){
        String inputString = inputText.getText().toString();
        Calculator calculator = new Calculator(inputString);
        outputText.setText(calculator.getOutput());
    }

    public void onClick_buttonMore(View view){
        View replaceableChild = null;
        for (int i = 0; i < gridLayout.getChildCount(); i++){
            replaceableChild = gridLayout.getChildAt(i);
            Log.i("",replaceableChild.getTag().toString());
            if(replaceableChild.getTag() == sideButtons.getTag()){
                Log.i("","sideButtons yes");
                gridLayout.removeView(replaceableChild);
                gridLayout.addView(additionalButtons,replaceableChild.getLayoutParams());
            }

            if(replaceableChild.getTag() == additionalButtons.getTag()){
                Log.i("","additionalButtons yes");
                gridLayout.removeView(replaceableChild);
                gridLayout.addView(sideButtons,replaceableChild.getLayoutParams());
            }
        }
    }

    public void onClick_buttonBackSpace(View view){
        String prevText = inputText.getText().toString();
        if(prevText.length() > 0){
            inputText.setText(prevText.substring(0, prevText.length()-1));
        }
    }

    public void onClick_buttonC(View view){
        inputText.setText("");
        outputText.setText("");
    }

    public void onClick_button1(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "1");
    }

    public void onClick_button2(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "2");
    }

    public void onClick_button3(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "3");
    }

    public void onClick_button4(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "4");
    }

    public void onClick_button5(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "5");
    }

    public void onClick_button6(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "6");
    }

    public void onClick_button7(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "7");
    }

    public void onClick_button8(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "8");
    }

    public void onClick_button9(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "9");
    }

    public void onClick_button0(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "0");
    }

    public void onClick_buttonDivide(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "/");
    }

    public void onClick_buttonMultiply(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "*");
    }

    public void onClick_buttonAdd(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "+");
    }

    public void onClick_buttonSubtract(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "-");
    }

    public void onClick_buttonDot(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + ".");
    }

    public void onClick_buttonLBracket(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "(");
    }

    public void onClick_buttonRBracket(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + ")");
    }

    public void onClick_buttonPower(View view){
        String prevText = inputText.getText().toString();
        inputText.setText(prevText + "^");
    }
}