package com.andy.gamesmultigame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView txtCredit, txtBet;
    ImageView btnDeal;

    ImageView[] btnCoins = new ImageView[5];

    int coinValue[] = {1, 5, 10, 25, 100};

    int iCredit, iBet, iTotalBet;


    Vector<ImageView> coin = new Vector<ImageView>();

    Vector<ImageView> cards = new Vector<ImageView>(52);

    Vector<ImageView> playerCards = new Vector<ImageView>();
    Vector<ImageView> dilarCards = new Vector<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        iCredit = 1000;

        btnCoins[0] = (ImageView)findViewById(R.id.coin1);
        btnCoins[1] = (ImageView)findViewById(R.id.coin5);
        btnCoins[2] = (ImageView)findViewById(R.id.coin10);
        btnCoins[3] = (ImageView)findViewById(R.id.coin25);
        btnCoins[4] = (ImageView)findViewById(R.id.coin100);

        txtBet = (TextView)findViewById(R.id.txtbet);
        txtBet.setTextColor(Color.WHITE);
        txtBet.setTextSize(30);

        txtCredit = (TextView)findViewById(R.id.txtcredit);
        txtCredit.setTextColor(Color.WHITE);
        txtCredit.setTextSize(30);
        txtCredit.setText(String.valueOf(iCredit));

        btnDeal = (ImageView) findViewById(R.id.btnDeal);

        btnDeal.setOnClickListener(this);
        btnDeal.setVisibility(View.INVISIBLE);

        for (int i = 0; i < btnCoins.length; i++){
            btnCoins[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        if (iCredit != 0){
            btnDeal.setVisibility(View.VISIBLE);
        }
        if(v.getId() == btnDeal.getId()){
            Log.d("D1", "HHHHHHHHHH");

            updateGame();
        }
        for (int i = 0; i < btnCoins.length; i++){
            if (v.getId() == btnCoins[i].getId()){
                Log.d("D1", String.valueOf(btnCoins[i].getId()));

                iBet = coinValue[i];
                iTotalBet += iBet;
                coin.add(btnCoins[i]);
                iCredit = iCredit-iBet;
                txtBet.setText(String.valueOf(iTotalBet));
                txtCredit.setText(String.valueOf(iCredit));
                coin.add(btnCoins[i]);
            }
        }
    }
    public void updateGame(){

    }

}