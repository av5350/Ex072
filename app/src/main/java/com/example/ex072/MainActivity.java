package com.example.ex072;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText aValue, bValue, cValue;

    String aString, bString, cString;

    Intent si;

    TextView shownRoots;

    Random rnd;

    final int CODE_SOLVER_ACTIVITY = 4567;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aValue = (EditText)findViewById(R.id.aValue);
        bValue = (EditText)findViewById(R.id.bValue);
        cValue = (EditText)findViewById(R.id.cValue);

        rnd = new Random();

        si = new Intent(this, SolverActivity.class);

        shownRoots = (TextView) findViewById(R.id.shownRoots);
    }

    public void getValues(View view) {
        aString = aValue.getText().toString();
        bString = bValue.getText().toString();
        cString = cValue.getText().toString();

        // if one of the cells is empty
        if (aString.equals("") || bString.equals("") || cString.equals("") ||
                aString.equals("-") || cString.equals("-") || bString.equals("-"))
        {
            Toast.makeText(this, "Enter a number in each cell!", Toast.LENGTH_SHORT).show();
        }
        else if (Float.parseFloat(aString) == 0){
            Toast.makeText(this, "a cannot be equal to 0!", Toast.LENGTH_SHORT).show();
        }
        else {
            si.putExtra("aValue", Double.parseDouble(aString));
            si.putExtra("bValue", Double.parseDouble(bString));
            si.putExtra("cValue", Double.parseDouble(cString));
            startActivityForResult(si, CODE_SOLVER_ACTIVITY);
        }
    }

    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);
        if (data_back != null) {
            shownRoots.setText("The answers are:\n" + data_back.getStringExtra("answer"));
        }
    }

    public void randomNums(View view) {
        // generate random values to the a,b,c text boxs
        // 9999999 -> the highest value that can shown in the tV without problems
        aValue.setText("" + rnd.nextInt(9999999));
        bValue.setText("" + rnd.nextInt(9999999));
        cValue.setText("" + rnd.nextInt(9999999));
    }
}