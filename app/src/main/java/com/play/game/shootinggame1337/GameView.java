package com.play.game.shootinggame1337;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.Display;
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
//        todo: add plater movement
//        leftArrow = new Arrow(this.context, false);
//        rightArrow = new Arrow(this.context, true);

        for (int ghostCounter =0; ghostCounter < 3; ghostCounter++) {

            Ghost ghost = new Ghost(this.context);
            ghosts.add(ghost);

            Bat bat = new Bat(this.context);
            bats.add(bat);
        }

        configureScore();

//        todo: work on health functionality
//        configureHealth();
    }

    private void configureScore() {

        this.scorePaint = new Paint();
        this.scorePaint.setTextSize(TEXT_SIZE);
        this.scorePaint.setTextAlign(Paint.Align.LEFT);
        this.scorePaint.setColor(Color.argb(255,255, 185, 0));
    }


    private void createSoundObjects() {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();

        this.soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .setAudioAttributes(audioAttributes)
                .build();

        this.shootSound = soundPool.load(this.context, R.raw.fire, 1);
        this.scoreSound = soundPool.load(this.context, R.raw.point, 1);
    }

    private void stretchBackGroundImage() {
        background = BitmapFactory.decodeResource(getResources(), R.drawable.cemetary_bg);

        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();

        Point outSize = new Point();

        display.getSize(outSize);

        displayWidth = outSize.x;
        displayHeight = outSize.y;

    }

}