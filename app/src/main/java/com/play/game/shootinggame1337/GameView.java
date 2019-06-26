package com.play.game.shootinggame1337;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.SoundPool;
import android.view.View;
import java.util.ArrayList;

public class GameView extends View {
    private static final float TEXT_SIZE= 60;
    public static int displayWidth;
    public static int displayHeight;
    final long UPDATE_MILLISECONDS = 120;
    ArrayList<Ghost> ghosts;
    ArrayList<Bat> bats;
    ArrayList<Explosion> explosions;
    private ArrayList<Fireball> fireballs;
    private Context context;
    private int batScore = 0;
    private int ghostScore = 0;
    private int playerHealth = 10;
    private int shootSound = 0;
    private int scoreSound = 0;
    private int totalScore = 0;
    private Paint scorePaint, healthPaint;

    Arrow leftArrow;

    Arrow rightArrow;
    Bitmap background;
    Hunter hunter;
    Hunter handler;

    Rect rectangle;

    Runnable runnable;
    SoundPool soundPool;

    public GameView(Context context) {
        super(context);

        this.context = context;

        stretchBackGroundImage();

        createGameObjects();

        createSoundObjects();

    }

    private void createGameObjects() {
        bats = new ArrayList<Bat>();
        explosions = new ArrayList<Explosion>();
        fireballs = new ArrayList<Fireball>();
        ghosts = new ArrayList<Ghost>();
        hunter = new Hunter(this.context);
//        leftArrow = new Arrow(this.context, false);
//        rightArrow = new Arrow(this.context, true);
    }

    private void createSoundObjects() {
    }

    private void stretchBackGroundImage() {
    }

}