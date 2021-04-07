package com.andy.gamesmultigame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class MainActivity extends Activity implements View.OnClickListener {


    Map<Integer, Integer> cardsAndPoint = new HashMap<Integer, Integer>();

    TextView txtCredit, txtBet, txtDilarPoint, txtPlayerPoint;
    ImageView btnDeal, btnHit, btnStand, btnDouble;

    ImageView p1,p2,p3,p4,p5;

    ImageView[] btnCoins = new ImageView[5];

    int coinValue[] = {1, 5, 10, 25, 100};

    int iCredit, iBet, iTotalBet, iDilarPoint, iPlayerPoint;

    boolean isDeal;

    int point[] = {
        11,2,3,4,5,6,7,8,9,10,10,10,10,
        11,2,3,4,5,6,7,8,9,10,10,10,10,
        11,2,3,4,5,6,7,8,9,10,10,10,10,
        11,2,3,4,5,6,7,8,9,10,10,10,10,
    };

    int cards[] = {
            R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13,
            R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,R.drawable.d11,R.drawable.d12,R.drawable.d13,
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,R.drawable.h11,R.drawable.h12,R.drawable.h13,
            R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,R.drawable.s11,R.drawable.s12,R.drawable.s13,
    };


    Vector<ImageView> coin = new Vector<ImageView>();


    ImageView[] PlayrCardsView = new ImageView[5];
    Vector<Integer> playerCards = new Vector<Integer>();
    Vector<Integer> playerPoints = new Vector<Integer>();

    ImageView[] DilarCardsView = new ImageView[5];
    Vector<Integer> dilarCards = new Vector<Integer>();
    Vector<Integer> dilarPoints = new Vector<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        isDeal = false;

        for (int i = 0; i<point.length; i++) {
            cardsAndPoint.put(cards[i], point[i]);
        }


        PlayrCardsView[0] = (ImageView) findViewById(R.id.player1);
        PlayrCardsView[1] = (ImageView) findViewById(R.id.player2);
        PlayrCardsView[2] = (ImageView) findViewById(R.id.player3);
        PlayrCardsView[3] = (ImageView) findViewById(R.id.player4);
        PlayrCardsView[4] = (ImageView) findViewById(R.id.player5);

        DilarCardsView[0] = (ImageView) findViewById(R.id.dealer1);
        DilarCardsView[1] = (ImageView) findViewById(R.id.dealer2);
        DilarCardsView[2] = (ImageView) findViewById(R.id.dealer3);
        DilarCardsView[3] = (ImageView) findViewById(R.id.dealer4);
        DilarCardsView[4] = (ImageView) findViewById(R.id.dealer5);

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

        txtDilarPoint = (TextView)findViewById(R.id.txtDilarPoint);
        txtDilarPoint.setTextColor(Color.WHITE);
        txtDilarPoint.setTextSize(20);
        txtDilarPoint.setVisibility(View.INVISIBLE);


        txtPlayerPoint = (TextView)findViewById(R.id.txtPlayerPoint);
        txtPlayerPoint.setTextColor(Color.WHITE);
        txtPlayerPoint.setTextSize(20);
        txtPlayerPoint.setVisibility(View.INVISIBLE);


        btnDeal = (ImageView) findViewById(R.id.btnDeal);
        btnHit = (ImageView) findViewById(R.id.btnHit);
        btnStand  = (ImageView) findViewById(R.id.btnStand);
        btnDouble  = (ImageView) findViewById(R.id.btnDouble);

        btnHit.setOnClickListener(this);
        btnHit.setVisibility(View.INVISIBLE);

        btnDouble.setOnClickListener(this);
        btnDouble.setVisibility(View.INVISIBLE);

        btnStand.setOnClickListener(this);
        btnStand.setVisibility(View.INVISIBLE);

        btnHit.setOnClickListener(this);
        btnHit.setVisibility(View.INVISIBLE);

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
            isDeal = true;
            updateGame();
            btnHit.setVisibility(View.VISIBLE);
            btnDouble.setVisibility(View.VISIBLE);
            btnStand.setVisibility(View.VISIBLE);
        }
        if(v.getId() == btnHit.getId()){



        }
        if(!isDeal) {
            for (int i = 0; i < btnCoins.length; i++) {
                if (v.getId() == btnCoins[i].getId()) {
                    Log.d("D1", String.valueOf(btnCoins[i].getId()));

                    iBet = coinValue[i];
                    iTotalBet += iBet;
                    coin.add(btnCoins[i]);
                    iCredit = iCredit - iBet;
                    txtBet.setText(String.valueOf(iTotalBet));
                    txtCredit.setText(String.valueOf(iCredit));
                    coin.add(btnCoins[i]);
                }
            }
        }
    }
    public void updateGame(){

        if (isDeal) {
            for (int i = 0; i < 3; i++) {
                int randomIndex = new Random().nextInt(cardsAndPoint.size());

                if (i < 2) {
                    playerCards.add(cards[randomIndex]);
                    playerPoints.add(point[randomIndex]);
                    iPlayerPoint += point[randomIndex];
                } else {
                    dilarCards.add(cards[randomIndex]);
                    dilarPoints.add(point[randomIndex]);
                    iDilarPoint = dilarPoints.get(0);
                }
                cardsAndPoint.remove(cards[randomIndex]);
            }

            for (int i = 0; i < playerCards.size(); i++) {
                PlayrCardsView[i].setImageResource(playerCards.get(i));
            }

            DilarCardsView[0].setImageResource(dilarCards.get(0));
            DilarCardsView[1].setImageResource(R.drawable.backcard);

            btnDeal.setVisibility(View.INVISIBLE);

            txtDilarPoint.setVisibility(View.VISIBLE);

            txtDilarPoint.setText(String.valueOf(iDilarPoint));

            txtPlayerPoint.setVisibility(View.VISIBLE);
            txtPlayerPoint.setText(String.valueOf(iPlayerPoint));
        }

    }

}