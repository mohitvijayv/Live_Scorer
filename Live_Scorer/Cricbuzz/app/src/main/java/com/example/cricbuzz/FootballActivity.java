package com.example.cricbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FootballActivity extends AppCompatActivity {
    private Button live_matches;
    private Button standings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        live_matches = findViewById(R.id.live_matches);
        standings = findViewById(R.id.standings);

        live_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FootballActivity.this, matchesActivity.class);
                startActivity(intent);
            }
        });
        standings.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FootballActivity.this, SFootball.class);
                startActivity(intent);
            }
        }));
    }
}
