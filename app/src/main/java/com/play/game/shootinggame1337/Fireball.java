package com.play.game.shootinggame1337;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Fireball {
    public Bitmap fireballImage;
    public float fireballX, fireballY, fireballVector;

    public Fireball(Context context) {
        fireballImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile);

        this.fireballX = GameView.displayWidth / 2 - this.getFireballWidth() / 2;
        this.fireballY = GameView.displayHeight - 275 - this.getFireballHeight() / 2;

        this.fireballVector = 50;
    }

    public int getFireballHeight() {

        return this.fireballImage.getHeight();
    }

    public int getFireballWidth() {

        return this.fireballImage.getWidth();
    }
}
