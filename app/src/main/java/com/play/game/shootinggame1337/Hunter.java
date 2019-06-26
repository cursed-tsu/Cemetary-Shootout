package com.play.game.shootinggame1337;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Hunter {
    private Bitmap[] hunter = new Bitmap[25];
    public static int hunterWidth, hunterHeight;
    public int hunterFrameNumber;
    public int hunterX, hunterY;

    public Hunter(Context context) {
        hunterFrameNumber = 0;

        setHunter(context);
    }

    public Bitmap getBitmap() {
        return hunter[hunterFrameNumber];
    }
    private void setHunter(Context context) {
        hunter[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_1);
        hunter[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_2);
        hunter[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_3);
        hunter[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_4);
        hunter[4] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_5);
        hunter[5] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_6);
        hunter[6] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_7);
        hunter[7] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_8);
        hunter[8] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_9);
        hunter[9] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_10);
        hunter[10] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_11);
        hunter[11] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_12);
        hunter[12] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_13);
        hunter[13] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_14);
        hunter[14] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_15);
        hunter[15] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_16);
        hunter[16] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_17);
        hunter[17] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_18);
        hunter[18] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_19);
        hunter[19] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_20);
        hunter[20] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_21);
        hunter[21] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_22);
        hunter[22] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_23);
        hunter[23] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_24);
        hunter[24] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hunter_25);

        hunterWidth = hunter[0].getWidth();
        hunterHeight = hunter[0].getHeight();
    }

}
