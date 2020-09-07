package com.example.sharkeatsfishapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Shark extends View {

    private Bitmap[] shark = new Bitmap[5];
    private int life;

    private int shark_positionX = 10;
    private int shark_positionY;

    private Bitmap[] fish = new Bitmap[2];
    private Bitmap[] rock = new Bitmap[2];

    private int fish_positionX;
    private int fish_positionY;

    private Paint fishPaint = new Paint();

    private int fish2_positionX;
    private int fish2_positionY;

    private int rock_positionX;
    private int rock_positionY;

    private Paint rockPaint = new Paint();

    private int total_score;

    private int shark_speed;

    private Bitmap background;

    Paint score = new Paint();

    private Bitmap[] hearts = new Bitmap[2];

    public Shark(Context context) {
        super(context);

        //shark first image
        shark[0]= BitmapFactory.decodeResource(getResources(), R.drawable.mini_shark);
        //shark second image
        shark[1]= BitmapFactory.decodeResource(getResources(), R.drawable.shark_cartoon);
        //shark third image
        shark[2]= BitmapFactory.decodeResource(getResources(), R.drawable.shark_open_mouth);
        //shark third image
        shark[3]= BitmapFactory.decodeResource(getResources(), R.drawable.angry_shark);
        //shark fifth image
        shark[4]= BitmapFactory.decodeResource(getResources(), R.drawable.old_shark);
        //fish 1
        fish[0]=BitmapFactory.decodeResource(getResources(),R.drawable.small_fish_60);
        //fish 2
        fish[1]=BitmapFactory.decodeResource(getResources(),R.drawable.fish_cartoon);
        //background image
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
        //rock image
        rock[0]=BitmapFactory.decodeResource(getResources(),R.drawable.rock);

        fishPaint.setAntiAlias(false);
        Paint fish2Paint = new Paint();
        fish2Paint.setAntiAlias(false);
        rockPaint.setAntiAlias(false);

        //paint the score of our shark
        score.setColor(Color.BLACK);
        score.setTextSize(70);
        score.setAntiAlias(true);

        //the hearts that remain to our shark
        hearts[0]=BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        //the black heart is the heart which our shark has lost
        hearts[1]=BitmapFactory.decodeResource(getResources(),R.drawable.black_heart);

        shark_positionY=550;//the first position of our shark
        total_score = 0;//the initial score
        life=3; // the initial life
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasY = getHeight();

        canvas.drawBitmap(background,0,0,null);//the position of the background

        int minSharkY = shark[0].getHeight()/2; //the minimum height of the shark to our screen
        int maxSharkY = canvasY - shark[0].getHeight() * 2;//the maximum height of the shark to our screen
        shark_positionY = shark_positionY + shark_speed;

        if (shark_positionY < minSharkY)
        {
            shark_positionY = minSharkY;
        }

        if (shark_positionY > maxSharkY)
        {
            shark_positionY = maxSharkY;
        }

        shark_speed = shark_speed+2;

        canvas.drawBitmap(shark[0],shark_positionX,shark_positionY,null);


        int fish_speed = 15;
        fish_positionX = fish_positionX - fish_speed;

            if(attackFish(fish_positionX,fish_positionY))
                 {
                        total_score = total_score + 10;//add to the score + 10 for each hit of fish 1
                        fish_positionX=  - 100;
                 }

            if(total_score >= 0 && total_score<100)
            {
                canvas.drawBitmap(shark[0],shark_positionX,shark_positionY,null);//draw 1 shark
            }
            else if (total_score >= 100 && total_score<200)
            {
                canvas.drawBitmap(background,0,0,null);//the position of the background
                canvas.drawBitmap(shark[1],shark_positionX,shark_positionY,null);//draw 2 shark
            }
            else if (total_score >= 200 && total_score<300)
            {
                canvas.drawBitmap(background,0,0,null);//the position of the background
                canvas.drawBitmap(shark[2],shark_positionX,shark_positionY,null);//draw 3 shark
            }
            else if (total_score >= 300 && total_score<400)
            {
                canvas.drawBitmap(background,0,0,null);//the position of the background
                canvas.drawBitmap(shark[3],shark_positionX,shark_positionY,null);//draw 4 shark
            }
            else if(total_score>=100)
            {
                canvas.drawBitmap(background,0,0,null);//the position of the background
                canvas.drawBitmap(shark[4],shark_positionX,shark_positionY,null);//draw 5 shark
            }

            if (fish_positionX < 0)
            {
                fish_positionX = canvasY +21;
                fish_positionY = (int) Math.floor(Math.random() * (maxSharkY-minSharkY))+ minSharkY;//change the position of the fish 1
            }


        canvas.drawBitmap(fish[0],fish_positionX,fish_positionY,fishPaint);


        int fish2_speed = 20;
        fish2_positionX = fish2_positionX - fish2_speed;

        if(attackFish(fish2_positionX,fish2_positionY))
        {
            total_score = total_score + 20;//add to the score + 20 for each hit of fish 2
            fish2_positionX=  - 100;
        }


        if (fish2_positionX < 0)
        {
            fish2_positionX = canvasY +21;
            fish2_positionY = (int) Math.floor(Math.random() * (maxSharkY-minSharkY))+ minSharkY;//change the position of the fish 2
        }


        canvas.drawBitmap(fish[1],fish2_positionX,fish2_positionY,fishPaint);


        int rock_speed = 25;
        rock_positionX = rock_positionX - rock_speed;

        if(attackFish(rock_positionX, rock_positionY))
        {

            rock_positionX=  - 100;
            life--;
            if (life == 0)
            {
                Toast.makeText(getContext(),"GAME OVER",Toast.LENGTH_SHORT).show();


                @SuppressLint("DrawAllocation") Intent finalIntent = new Intent(getContext(),FinalActivity.class);
                finalIntent.putExtra("final_score",total_score);
                getContext().startActivity(finalIntent);


            }
        }


        if (rock_positionX < 0)
        {
            rock_positionX = canvasY +21;
            rock_positionY = (int) Math.floor(Math.random() * (maxSharkY-minSharkY))+ minSharkY;//change the position of the rock
        }

        canvas.drawBitmap(rock[0],rock_positionX,rock_positionY,rockPaint);//draw the rock


        canvas.drawText("Score : " + total_score, 20 ,60 ,score);//the position of the score
        for(int counter=0;counter<3;counter++)
        {
            int x=(int) (450 + hearts[0].getWidth()*1.5*counter);//the position of the hearts
            int y=15;

            if (counter<life)
            {
                canvas.drawBitmap(hearts[0],x,y,null);
            }
            else
            {
                canvas.drawBitmap(hearts[1],x,y,null);
            }
        }

    }

    public boolean attackFish(int x, int y) //if shark attack
    {

        return shark_positionX < x && x < (shark_positionX + shark[0].getWidth()) && shark_positionY < y && y < (shark_positionY + shark[0].getHeight());

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {//touch the shark in order to go up

        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {

            shark_speed = -22;
        }

        return true;

    }
}
