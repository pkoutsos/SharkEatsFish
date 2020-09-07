package com.example.sharkeatsfishapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class FinalActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        String final_score = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("final_score")).toString();

        Button button_play_again = findViewById(R.id.play_again);
        TextView score = findViewById(R.id.score);

        button_play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameAgain();
            }
        });

        score.setText("Score : " + final_score);

    }


    public void openGameAgain()
    {
        Intent intent = new Intent(FinalActivity.this,MainActivity.class);
        startActivity(intent);
    }
}