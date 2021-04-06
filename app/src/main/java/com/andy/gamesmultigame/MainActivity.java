package com.andy.gamesmultigame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView txtCredit, txtBet;
    int iCredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        iCredit = 1000;

        txtBet = (TextView)findViewById(R.id.txtbet);
        txtBet.setTextColor(Color.WHITE);
        txtBet.setTextSize(30);

        txtCredit = (TextView)findViewById(R.id.txtcredit);
        txtCredit.setTextColor(Color.WHITE);
        txtCredit.setTextSize(30);
        txtCredit.setText(String.valueOf(iCredit));

    }

    @Override
    public void onClick(View v) {

    }
}