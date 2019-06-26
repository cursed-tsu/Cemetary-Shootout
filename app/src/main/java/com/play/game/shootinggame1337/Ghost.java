package com.play.game.shootinggame1337;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Ghost extends FlyingMob{

    public int ghostY, ghostX, ghostVelocity, ghostFrameNumber;

    private Bitmap[] ghost = new Bitmap[12];

    public Ghost(Context context) {

        super.random = new Random();

        setFlyingMonster(context);

        resetPosition();
    }

    @Override
    public Bitmap getBitmap() {
        return ghost[ghostFrameNumber];
    }

    @Override
    public int getWidth() {
        return ghost[0].getWidth();
    }

    @Override
    public int getHeight() {
        return ghost[0].getHeight();
    }

    @Override
    public void setFlyingMonster(Context context) {

        ghost[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_0);
        ghost[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_1);
        ghost[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_2);
        ghost[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_3);
        ghost[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_4);
        ghost[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_5);
        ghost[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_6);
        ghost[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_7);
        ghost[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_8);
        ghost[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_9);
        ghost[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_10);
        ghost[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_11);

    }

    @Override
    public void resetPosition() {
        ghostX = GameView.displayWidth + super.random.nextInt(1200);
        ghostY = super.random.nextInt(300);

        ghostVelocity = 8 + super.random.nextInt(13);

        ghostFrameNumber = 0;
    }
}
