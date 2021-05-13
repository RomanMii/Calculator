package com.ti38b.calculator;

import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ti38b.calculator.calculatorLogic.Calculator;
import com.ti38b.calculator.historyDataBase.DBhelper;

public class MainActivity extends AppCompatActivity {

    TextView inputText;
    TextView outputText;
    androidx.gridlayout.widget.GridLayout gridLayout;
    ViewFlipper viewFlipper;
    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = getLayoutInflater();
        inputText = findViewById(R.id.input);
        outputText = findViewById(R.id.output);

        gridLayout = findViewById(R.id.buttonsID);
        viewFlipper = findViewById(R.id.viewFlipper);
        dBhelper = new DBhelper(this);
    }

    public void onClick_history(View view){

        Cursor cursor = dBhelper.select();
        if(cursor.getCount() == 0){
            Toast.makeText(MainActivity.this,"history is empty", 1);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
            stringBuffer.append(cursor.getString(0) + "\n");
            stringBuffer.append("=" + cursor.getString(1) + "\n");
            stringBuffer.append("_________" + "\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setMessage(stringBuffer.toString());
        builder.show();
    }

    public void onClick_buttonEqual(View view){
        String inputString = inputText.getText().toString();
        Calculator calculator = new Calculator(inputString);

        String outputString = calculator.getOutput();
        outputText.setText(outputString);

        dBhelper.insert(inputString,outputString);
    }

    public void onClick_buttonMore(View view){
        if(viewFlipper.getDisplayedChild() == 0){
            viewFlipper.setDisplayedChild(1);
        }else if(viewFlipper.getDisplayedChild() == 1){
            viewFlipper.setDisplayedChild(0);
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