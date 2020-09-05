package com.example.ex072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class SolverActivity extends AppCompatActivity {
    Intent gi;

    double aValue, bValue, cValue, root1, root2, discriminant;

    String rootsAnswer;

    TextView shownRoots;

    WebView wV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        shownRoots = (TextView) findViewById(R.id.shownRoots);

        wV = (WebView)findViewById(R.id.wV);
        wV.getSettings().setJavaScriptEnabled(true);
        wV.setWebViewClient(new MyWebViewClient());

        gi = getIntent();
        aValue = gi.getDoubleExtra("aValue", 1);
        bValue = gi.getDoubleExtra("bValue", 1);
        cValue = gi.getDoubleExtra("cValue", 1);

        solveEquation();
        createAnswer();

        shownRoots.setText(rootsAnswer);
        wV.loadUrl("https://www.google.com/search?q=" + aValue + "x%5E2%2B" + bValue + "*x%2B" + cValue);
    }

    public void solveEquation (){
        // calculate the discriminant -> b^2 - 4ac
        discriminant = Math.pow(bValue, 2) - 4 * aValue * cValue;

        // calculate the roots
        root1 = (-bValue + Math.sqrt(discriminant)) / (2 * aValue);
        root2 = (-bValue - Math.sqrt(discriminant)) / (2 * aValue);
    }

    public void createAnswer(){
        // there are 2 different roots
        if (discriminant > 0){
            rootsAnswer = "X1 = " + root1 + "\nX2 = " + root2;
        }
        // there is only 1 root
        else if (discriminant == 0){
            rootsAnswer = "X = " + root1;
        }
        // no real roots (there is imaginary part)
        else {
            rootsAnswer = "There is no solution!";
        }
    }

    public void returnMain(View view) {
        gi.putExtra("answer", rootsAnswer);
        setResult(RESULT_OK, gi);
        finish();
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}