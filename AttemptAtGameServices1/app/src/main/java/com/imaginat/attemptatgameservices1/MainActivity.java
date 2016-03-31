package com.imaginat.attemptatgameservices1;

import android.os.Bundle;

import com.google.example.games.basegameutils.BaseGameActivity;

public class MainActivity extends BaseGameActivity {


    //appID: 339917493084
    //clientID: 339917493084-0ejes79t86sgndi578825s2al2gqaaja.apps.googleusercontent.com
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getGameHelper().setMaxAutoSignInAttempts(2);
    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }
}
