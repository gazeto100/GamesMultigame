package com.andy.gamesmultigame;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class MainActivity extends Activity implements View.OnClickListener {


    Map<Integer, Integer> cardsAndPoint = new HashMap<Integer, Integer>();

    TextView txtCredit, txtBet, txtDilarPoint, txtPlayerPoint;
    ImageView btnDeal, btnHit, btnStand, btnDouble;

    ImageView imgTextLost, imgTextWon;

    ImageView[] btnCoins = new ImageView[5];

    int coinValue[] = {1, 5, 10, 25, 100};

    Button startGame;

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


    ImageView[] PlayrCardsView = new ImageView[7];
    Vector<Integer> playerCards = new Vector<Integer>();
    Vector<Integer> playerPoints = new Vector<Integer>();

    ImageView[] DilarCardsView = new ImageView[7];
    Vector<Integer> dilarCards = new Vector<Integer>();
    Vector<Integer> dilarPoints = new Vector<Integer>();

    private CountDownTimer countDownTimer;

    private static final long START_TIME_IN_MILLIS = 600000;
    private long mTimeLeftMillis = START_TIME_IN_MILLIS;

    private boolean mTimerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        isDeal = false;

        startGame = (Button)findViewById(R.id.btnStart);
        startGame.setVisibility(View.INVISIBLE);
        startGame.setOnClickListener(this);

        for (int i = 0; i<point.length; i++) {
            cardsAndPoint.put(cards[i], point[i]);
        }


        PlayrCardsView[0] = (ImageView) findViewById(R.id.player1);
        PlayrCardsView[1] = (ImageView) findViewById(R.id.player2);
        PlayrCardsView[2] = (ImageView) findViewById(R.id.player3);
        PlayrCardsView[3] = (ImageView) findViewById(R.id.player4);
        PlayrCardsView[4] = (ImageView) findViewById(R.id.player5);
        PlayrCardsView[5] = (ImageView) findViewById(R.id.player6);
        PlayrCardsView[6] = (ImageView) findViewById(R.id.player7);

        DilarCardsView[0] = (ImageView) findViewById(R.id.dealer1);
        DilarCardsView[1] = (ImageView) findViewById(R.id.dealer2);
        DilarCardsView[2] = (ImageView) findViewById(R.id.dealer3);
        DilarCardsView[3] = (ImageView) findViewById(R.id.dealer4);
        DilarCardsView[4] = (ImageView) findViewById(R.id.dealer5);
        DilarCardsView[5] = (ImageView) findViewById(R.id.dealer6);
        DilarCardsView[6] = (ImageView) findViewById(R.id.dealer7);

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

        imgTextLost = (ImageView) findViewById(R.id.imgTextlost);
        imgTextLost.setVisibility(View.INVISIBLE);

        btnDeal = (ImageView) findViewById(R.id.btnDeal);
        btnHit = (ImageView) findViewById(R.id.btnHit);
        btnStand  = (ImageView) findViewById(R.id.btnStand);
        btnDouble  = (ImageView) findViewById(R.id.btnDouble);

        float xB = (float)(width/2);
        btnDeal.setX(xB-72);
        btnStand.setX(xB-72);


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

        if(!isDeal) {
            boolean bNoMoney = false;
            for (int i = 0; i < btnCoins.length; i++) {
                if (v.getId() == btnCoins[i].getId()) {

                    if (iCredit - coinValue[i] < 0 ){
                        Toast.makeText(getApplicationContext(), "No Money", Toast.LENGTH_SHORT).show();
                        bNoMoney = true;
                        break;
                    }

                    iBet = coinValue[i];
                    iTotalBet += iBet;
                    coin.add(btnCoins[i]);
                    iCredit = iCredit - iBet;
                    txtBet.setText(String.valueOf(iTotalBet));
                    txtCredit.setText(String.valueOf(iCredit));
                    coin.add(btnCoins[i]);
                }
            }
            if (!bNoMoney) {
                btnDeal.setVisibility(View.VISIBLE);
            }
        }

        if(v.getId() == btnDeal.getId()){
            isDeal = true;
            updateGame();
            //startTimer();
            btnHit.setVisibility(View.VISIBLE);
            btnDouble.setVisibility(View.VISIBLE);
            btnStand.setVisibility(View.VISIBLE);
            for (ImageView i: btnCoins){
                i.setVisibility(View.INVISIBLE);
            }
        }
        if(v.getId() == btnHit.getId()){

            HitLog();
            clearAllUp21();

        }

        if (v.getId() == btnStand.getId()){
            StandLogic();
        }

        if (v.getId() == startGame.getId()){
            StartNewGame();
            startGame.setVisibility(View.INVISIBLE);
        }
        //updateCountDownText();
    }


    public void HitLog(){

        int randomIndex = new Random().nextInt(cardsAndPoint.size());

        int size = playerCards.size();

        playerCards.add(cards[randomIndex]);
        playerPoints.add(point[randomIndex]);
        iPlayerPoint += point[randomIndex];

        PlayrCardsView[size].setImageResource(playerCards.get(size));
        txtPlayerPoint.setText(String.valueOf(iPlayerPoint));

        cardsAndPoint.remove(cards[randomIndex]);


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

    public void clearAllUp21(){
        if (iPlayerPoint > 21){
            btnHit.setVisibility(View.INVISIBLE);
            btnStand.setVisibility(View.INVISIBLE);
            btnDouble.setVisibility(View.INVISIBLE);

            iBet = 0;
            iTotalBet = 0;
            txtBet.setText(String.valueOf(iBet));

            imgTextLost.setImageResource(R.drawable.lost);
            imgTextLost.setVisibility(View.VISIBLE);

            startGame.setVisibility(View.VISIBLE);

            int randomIndex = new Random().nextInt(cardsAndPoint.size());
            dilarPoints.add(point[randomIndex]);
            iDilarPoint += point[randomIndex];
            dilarCards.add(cards[randomIndex]);
            DilarCardsView[1].setImageResource(dilarCards.get(1));
            txtDilarPoint.setText(String.valueOf(iDilarPoint));
        }
    }

    public void StartNewGame(){

        btnHit.setVisibility(View.INVISIBLE);
        btnStand.setVisibility(View.INVISIBLE);
        btnDouble.setVisibility(View.INVISIBLE);
        //btnDeal.setVisibility(View.INVISIBLE);
        isDeal = false;


        playerCards.clear();
        playerPoints.clear();
        iPlayerPoint = 0;
        txtPlayerPoint.setVisibility(View.INVISIBLE);

        txtCredit.setText(String.valueOf(iCredit));
        iTotalBet = 0;
        iBet = 0;
        txtBet.setText(String.valueOf(iBet));

        dilarCards.clear();
        dilarPoints.clear();
        iDilarPoint = 0;
        txtDilarPoint.setVisibility(View.INVISIBLE);

        cardsAndPoint.clear();

        imgTextLost.setVisibility(View.INVISIBLE);

        for (int i = 0; i < PlayrCardsView.length; i++){
            PlayrCardsView[i].setImageResource(0);
            DilarCardsView[i].setImageResource(0);
        }

        for (int i = 0; i<point.length; i++) {
            cardsAndPoint.put(cards[i], point[i]);
        }

        for (ImageView i: btnCoins){
            i.setVisibility(View.VISIBLE);
        }
    }

    public void StandLogic(){
        int count = 1;
        while (iDilarPoint<17){
            int randomIndex = new Random().nextInt(cardsAndPoint.size());
            dilarPoints.add(point[randomIndex]);
            iDilarPoint += point[randomIndex];
            dilarCards.add(cards[randomIndex]);
            DilarCardsView[count].setImageResource(dilarCards.get(count));
            count++;
        }
        txtDilarPoint.setText(String.valueOf(iDilarPoint));

        if(iDilarPoint > iPlayerPoint && iDilarPoint <= 21){
            imgTextLost.setImageResource(R.drawable.lost);
            imgTextLost.setVisibility(View.VISIBLE);
        }else if(iDilarPoint < iPlayerPoint){
            imgTextLost.setImageResource(R.drawable.won);
            imgTextLost.setVisibility(View.VISIBLE);
            iCredit = iCredit+iTotalBet*2;
        }else if(iDilarPoint == iPlayerPoint){
            imgTextLost.setImageResource(R.drawable.pushed);
            imgTextLost.setVisibility(View.VISIBLE);
            iCredit = iCredit+iTotalBet;
        }else if (iDilarPoint > 21){
            imgTextLost.setImageResource(R.drawable.won);
            imgTextLost.setVisibility(View.VISIBLE);
            iCredit = iCredit+iTotalBet*2;
        }else if (iPlayerPoint == 21 && iDilarPoint != 21){
            imgTextLost.setImageResource(R.drawable.won);
            imgTextLost.setVisibility(View.VISIBLE);
            iCredit = iCredit+iTotalBet*2;
        }
        startGame.setVisibility(View.VISIBLE);
        btnDouble.setVisibility(View.INVISIBLE);
        btnHit.setVisibility(View.INVISIBLE);
        btnStand.setVisibility(View.INVISIBLE);
    }


    private void startTimer(){

        countDownTimer = new CountDownTimer(mTimeLeftMillis, 30) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTimeLeftMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText(){
     //   int minutes = (int) mTimeLeftMillis / 1000 / 60;
     //   int seconds = (int) mTimeLeftMillis / 1000 % 60;

        float intX = PlayrCardsView[0].getX();
        intX += 20;
        PlayrCardsView[0].setX(intX);
    }

}