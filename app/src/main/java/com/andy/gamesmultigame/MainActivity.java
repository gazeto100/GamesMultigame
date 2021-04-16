package com.andy.gamesmultigame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintsChangedListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBjStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBjStart = (Button) findViewById(R.id.btnBjGame);

        btnBjStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btnBjStart.getId()){
            startActivity(new Intent(this, BlackjackActivity.class));
        }

    }
}