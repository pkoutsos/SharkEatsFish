package com.example.sharkeatsfishapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Playnow extends AppCompatActivity {

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playnow);
        Button button_play = findViewById(R.id.play);
        Button button_exit = findViewById(R.id.exit);

        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame();
            }
        });

        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExit();
            }
        });

        imageButton = findViewById(R.id.info);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Playnow.this,Information.class);//open Information.class
                startActivity(intent);
            }
        });
    }

    public void openGame()
    {
        Intent intent=new Intent(Playnow.this,MainActivity.class);//open MainActivity.class
        startActivity(intent);
    }

    public void openExit()
    {
        finish();//close program
        System.exit(0);
    }
}