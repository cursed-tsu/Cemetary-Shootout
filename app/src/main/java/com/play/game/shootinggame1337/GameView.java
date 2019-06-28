package com.play.game.shootinggame1337;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View {
    private static final float TEXT_SIZE = 60;
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
    Handler handler;

    Rect rectangle;

    Runnable runnable;
    SoundPool soundPool;

    public GameView(Context context) {
        super(context);

        this.context = context;

        stretchBackGroundImage();

        createGameObjects();

        createSoundObjects();

        handler = new Handler();

        rectangle = new Rect(0, 0, displayWidth, displayHeight);

        runnable = (Runnable) () -> {
            invalidate();
        };
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

        for (int ghostCounter = 0; ghostCounter < 3; ghostCounter++) {

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
        this.scorePaint.setColor(Color.argb(255, 255, 185, 0));
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            Log.i("onTouchEvent", "is tapped downward.");

            Log.i("onTouchEvent", "touch X: " + touchX + ". touch Y: " + touchY +
                    ".  The display height is " + displayHeight + ".  The hunter position is at " +
                    (displayHeight - 100) + ".  ");

            if (touchX >= (displayWidth / 2 - hunter.hunterWidth / 2) &&
                    touchX <= (displayWidth / 2 + hunter.hunterWidth / 2) &&
                    touchY <= (displayHeight - 100)) {

                Log.i("Hunter", "is tapped downward. touchX: " + touchX + ". touchY: " + touchY);

//            todo: create ammo loot to pick up

                if (this.fireballs.size() < 3) {
                    Fireball fireball = new Fireball(context);
                    fireballs.add(fireball);

                    if (shootSound != 0) {
                        this.soundPool.play(shootSound, 1, 1, 0, 0, 1);

                    }
                }
            }

        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background, null, rectangle, null);

        drawObjects(canvas);

        handler.postDelayed(runnable, UPDATE_MILLISECONDS);

    }

    private void drawObjects(Canvas canvas) {

        for (int fireballCounter = 0; fireballCounter < fireballs.size(); fireballCounter++) {
            if (fireballs.get(fireballCounter).fireballY > -fireballs.get(fireballCounter).getFireballHeight()) {
                fireballs.get(fireballCounter).fireballY -= fireballs.get(fireballCounter).fireballVector;

                canvas.drawBitmap(fireballs.get(fireballCounter).fireballImage, fireballs.get(fireballCounter).fireballX, fireballs.get(fireballCounter).fireballY, null);

                Log.i("drawObjects", "fireball X: " + fireballs.get(fireballCounter).fireballX + ".  fireball Y: " +
                        fireballs.get(fireballCounter).fireballY + ".  ghost Y: " + ghosts.get(0).ghostY +
                        " ghost height: " + ghosts.get(0).getHeight());

                if (isGhostCollision(fireballCounter, 0)) {

                    Log.i("drawObjects", "Explosion!! fireball X: " + fireballs.get(fireballCounter).fireballX + ".  fireball Y: " +
                            fireballs.get(fireballCounter).fireballY + ".  ghost Y: " + ghosts.get(0).ghostY +
                            " ghost height: " + ghosts.get(0).getHeight());

                    doExplosionForGhosts(context, ghosts, explosions, 0);

                    ghosts.get(0).resetPosition();

                    ghostScore++;

                    fireballs.remove(fireballCounter);

                    this.playScoreSound();


                } else if (isGhostCollision(fireballCounter, 1)) {

                    Log.i("drawObjects", "Explosion!! fireball X: " + fireballs.get(fireballCounter).fireballX + ".  fireball Y: " +
                            fireballs.get(fireballCounter).fireballY + ".  ghost Y: " + ghosts.get(1).ghostY +
                            " ghost height: " + ghosts.get(1).getHeight());

                    doExplosionForGhosts(context, ghosts, explosions, 1);

                    ghosts.get(1).resetPosition();

                    ghostScore++;

                    fireballs.remove(fireballCounter);

                    this.playScoreSound();


                } else if (isGhostCollision(fireballCounter, 2)) {

                    Log.i("drawObjects", "Explosion!! fireball X: " + fireballs.get(fireballCounter).fireballX + ".  fireball Y: " +
                            fireballs.get(fireballCounter).fireballY + ".  ghost Y: " + ghosts.get(2).ghostY +
                            " ghost height: " + ghosts.get(2).getHeight());

                    doExplosionForGhosts(context, ghosts, explosions, 2);

                    ghosts.get(2).resetPosition();

                    ghostScore++;

                    fireballs.remove(fireballCounter);

                    this.playScoreSound();


                } else if (isBatCollision(fireballCounter, 0)) {

                    Log.i("drawObjects", "Explosion!! fireball X: " + fireballs.get(fireballCounter).fireballX + ".  fireball Y: " +
                            fireballs.get(fireballCounter).fireballY + ".  bat Y: " + bats.get(0).batY +
                            " bat height: " + bats.get(0).getHeight());

                    doExplosionForBats(context, bats, explosions, 0);

                    bats.get(0).resetPosition();

                    batScore++;

                    fireballs.remove(fireballCounter);

                    this.playScoreSound();


                } else if (isBatCollision(fireballCounter, 1)) {

                    Log.i("drawObjects", "Explosion!! fireball X: " + fireballs.get(fireballCounter).fireballX + ".  fireball Y: " +
                            fireballs.get(fireballCounter).fireballY + ".  bat Y: " + bats.get(1).batY +
                            " bat height: " + bats.get(1).getHeight());

                    doExplosionForBats(context, bats, explosions, 1);

                    bats.get(1).resetPosition();

                    batScore++;

                    fireballs.remove(fireballCounter);

                    this.playScoreSound();


                } else if (isBatCollision(fireballCounter, 2)) {

                    Log.i("drawObjects", "Explosion!! fireball X: " + fireballs.get(fireballCounter).fireballX + ".  fireball Y: " +
                            fireballs.get(fireballCounter).fireballY + ".  bat Y: " + bats.get(2).batY +
                            " bat height: " + bats.get(2).getHeight());

                    doExplosionForBats(context, bats, explosions, 2);

                    bats.get(2).resetPosition();

                    batScore++;

                    fireballs.remove(fireballCounter);

                    this.playScoreSound();


                }
            } else {
                fireballs.remove(fireballCounter);
            }
        }

        drawExplosions(canvas);

        drawHunter(canvas);

        drawGhosts(canvas);

        drawBats(canvas);

        drawScore(canvas);


    }

    private void drawScore(Canvas canvas) {
        Log.i("drawScore", "Updating player score" + this.batScore + this.ghostScore);

        this.totalScore = ((this.batScore * 2) + this.ghostScore);

        canvas.drawText("Score: " + totalScore, 0, TEXT_SIZE, this.scorePaint);
    }

    private boolean isBatCollision(int fireballCounter, int batIndex) {
        return fireballs.get(fireballCounter).fireballX >= bats.get(batIndex).batX &&
                (fireballs.get(fireballCounter).fireballX + fireballs.get(fireballCounter).getFireballWidth()) <= (bats.get(batIndex).batX + bats.get(batIndex).getWidth()) &&
                fireballs.get(fireballCounter).fireballY >= bats.get(batIndex).batY &&
                fireballs.get(fireballCounter).fireballY <= (bats.get(batIndex).batY + bats.get(batIndex).getHeight());
    }

    private boolean isGhostCollision(int fireballCounter, int ghostIndex) {
        return fireballs.get(fireballCounter).fireballX >= ghosts.get(ghostIndex).ghostX &&
                (fireballs.get(fireballCounter).fireballX + fireballs.get(fireballCounter).getFireballWidth()) <= (ghosts.get(ghostIndex).ghostX + ghosts.get(ghostIndex).getWidth()) &&
                fireballs.get(fireballCounter).fireballY >= ghosts.get(ghostIndex).ghostY &&
                fireballs.get(fireballCounter).fireballY <= (ghosts.get(ghostIndex).ghostY + ghosts.get(ghostIndex).getHeight());
    }

    private static void doExplosionForGhosts(Context context, ArrayList<Ghost> ghosts, ArrayList<Explosion> explosions, int mobIndex) {

        Explosion explosion = new Explosion(context);

        explosion.explosionX = ghosts.get(mobIndex).ghostX + ghosts.get(mobIndex).getWidth() / 2 - explosion.getExplosionWidth() / 2;

        explosion.explosionY = ghosts.get(mobIndex).ghostY + ghosts.get(mobIndex).getHeight() / 2 - explosion.getExplosionHeight() / 2;

        explosions.add(explosion);

    }

    private static void doExplosionForBats(Context context, ArrayList<Bat> bats,
                                           ArrayList<Explosion> explosions, int mobIndex) {
        Explosion explosion = new Explosion(context);

        explosion.explosionX = bats.get(mobIndex).batX + bats.get(mobIndex).getWidth() / 2 -
                explosion.getExplosionWidth() / 2;

        explosion.explosionY = bats.get(mobIndex).batY + bats.get(mobIndex).getHeight() / 2 -
                explosion.getExplosionHeight() / 2;

        explosions.add(explosion);
    }

    private void drawExplosions(Canvas canvas) {
        for (int explosionCounter = 0; explosionCounter < explosions.size(); explosionCounter++) {
            Log.i("drawExplosions", "We have an explosion");

            canvas.drawBitmap(explosions.get(explosionCounter).getExplosion(explosions
                            .get(explosionCounter).explosionFrameNumber),
                    explosions.get(explosionCounter).explosionX, explosions
                            .get(explosionCounter).explosionY, null);

            explosions.get(explosionCounter).explosionFrameNumber++;

            // there are only 9 images
            if (explosions.get(explosionCounter).explosionFrameNumber > 8) {
                explosions.remove(explosionCounter);
            }
        }
    }

    private void playScoreSound() {
        if (scoreSound != 0) {
            this.soundPool.play(scoreSound, 1, 1, 0, 0, 1);
        }
    }

    private void drawDirectionArrows(Canvas canvas) {
//        canvas.drawBitmap(leftArrow.getBitmap(), (displayWidth / 4 - leftArrow.getWidth() / 2),
//                displayHeight - leftArrow.getHeight(), null);
//
//        canvas.drawBitmap(rightArrow.getBitmap(), ((displayWidth / 4) * 3 - rightArrow.getWidth() / 2),
//                displayHeight - rightArrow.getHeight(), null);
    }

    /**
     * draw the hunter at the bottom of the screen
     * <p>
     * Full Screen Needed:
     * Remember, the canvas is less than the full screen because
     * it doesn't include the notification bar at the top
     * Need to implement full screen to avoid items at bottom
     * getting cut off
     * <p>
     * displayHeight: 1794
     * hunterHeight: 525
     */
    private void drawHunter(Canvas canvas) {
//        Log.i("drawHunter", "screen display height: " + displayHeight + ".  hunter height: " +
//                hunter.hunterHeight);

        //hunter.hunterHeight
        canvas.drawBitmap(hunter.getBitmap(), (displayWidth / 2 - hunter.hunterWidth / 2),
                displayHeight - 300, null);
    }

    private void drawBats(Canvas canvas) {
        for (int batCounter = 0; batCounter < bats.size(); batCounter++) {
            canvas.drawBitmap(bats.get(batCounter).getBitmap(), bats.get(batCounter).batX,
                    bats.get(batCounter).batY, null);

            bats.get(batCounter).batFrameNumber++;

            if (bats.get(batCounter).batFrameNumber > 8) {
                bats.get(batCounter).batFrameNumber = 0;
            }

            // move bat towards the left side of the screen
            bats.get(batCounter).batX -= bats.get(batCounter).batVelocity;


//        when bat goes off the left side of the screen
//        randomly change position and velocity of "new" bat
            if (bats.get(batCounter).batX < -bats.get(batCounter).getWidth()) {
                bats.get(batCounter).resetPosition();
            }
        }
    }

    private void drawGhosts(Canvas canvas) {
        for (int ghostCounter = 0; ghostCounter < ghosts.size(); ghostCounter++) {
            canvas.drawBitmap(ghosts.get(ghostCounter).getBitmap(), ghosts.get(ghostCounter).ghostX, ghosts.get(ghostCounter).ghostY, null);

            ghosts.get(ghostCounter).ghostFrameNumber++;

            if (ghosts.get(ghostCounter).ghostFrameNumber > 11) {
                ghosts.get(ghostCounter).ghostFrameNumber = 0;
            }

            // move ghost towards the left side of the screen
            ghosts.get(ghostCounter).ghostX -= ghosts.get(ghostCounter).ghostVelocity;


//        when ghost goes off the left side of the screen
//        randomly change position and velocity of "new" ghost
            if (ghosts.get(ghostCounter).ghostX < -ghosts.get(ghostCounter).getWidth()) {
                ghosts.get(ghostCounter).resetPosition();
            }
        }
    }

}