package com.play.game.shootinggame1337;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startButtonOnClick(View view) {
        Log.i("ImageButton", "Start Button Clicked");
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }
}

