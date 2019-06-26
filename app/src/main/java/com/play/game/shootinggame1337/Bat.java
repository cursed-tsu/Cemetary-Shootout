package com.play.game.shootinggame1337;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Bat extends FlyingMob  {

    public int batY, batX, batVelocity, batFrameNumber;

    private Bitmap[] bat = new Bitmap[9];

    public Bat(Context context) {

        super.random = new Random();

        setFlyingMonster(context);

        resetPosition();
    }

    @Override
    public Bitmap getBitmap() {
        return bat[batFrameNumber];
    }

    @Override
    public int getWidth() {
        return bat[0].getWidth();
    }

    @Override
    public int getHeight() {
        return bat[0].getHeight();
    }

    @Override
    public void setFlyingMonster(Context context) {

        bat[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_0);
        bat[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_1);
        bat[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_2);
        bat[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_3);
        bat[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_4);
        bat[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_5);
        bat[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_6);
        bat[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_7);
        bat[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_8);
    }

    @Override
    public void resetPosition() {

            batX = GameView.displayWidth + super.random.nextInt(1200);
            batY = super.random.nextInt(300);

            batVelocity = 8 + super.random.nextInt(13);

            batFrameNumber = 0;
    }
}
