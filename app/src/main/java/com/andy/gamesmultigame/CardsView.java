package com.andy.gamesmultigame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import java.util.Vector;

public class CardsView extends View implements View.OnClickListener {

    Vector<Integer> cards = new Vector<Integer>();

    Vector<ImageView> playerCards = new Vector<ImageView>();
    Vector<ImageView> dilarCards = new Vector<ImageView>();

    public CardsView(Context context) {
        super(context);
    }

    @Override
    public void onClick(View v) {

    }

}
