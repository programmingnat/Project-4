package com.imaginat.tetriscombat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button singlePlayer = (Button)findViewById(R.id.singlePlayerButton);
        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button multiPlayer = (Button)findViewById(R.id.multiPlayerButton);
        multiPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMultiplayerSetup = new Intent(MainActivity.this,MultiPlayerActivity.class);
                startActivity(toMultiplayerSetup);
            }
        });
    }
}
