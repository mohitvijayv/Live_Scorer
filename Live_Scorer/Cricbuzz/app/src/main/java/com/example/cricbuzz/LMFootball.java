package com.example.cricbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LMFootball extends AppCompatActivity {

    private Button increase_team1;
    private Button increase_team2;
    private Button decrease_team1;
    private Button decrease_team2;
    private Button set_status;
    private TextView team1_score;
    private TextView team2_score;
    private TextView team1_name;
    private TextView team2_name;

    private int mscore1;
    private int mscore2;

    static final String State_Score_1 = "Team 1 Score";
    static final String State_Score_2 = "Team 2 Score";

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lmfootball);

        Bundle b = getIntent().getExtras();
        String txt_team1Name = b.getString("t1Name");
        String txt_team2Name = b.getString("t2Name");
        final String txt_matchID = b.getString("matchID");

        CreateScore sc = new CreateScore(String.valueOf(mscore1), String.valueOf(mscore2));

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("matches").child(txt_matchID).child("score").setValue(sc);
        mDatabase.child("matches").child(txt_matchID).child("status").setValue("ongoing");

        team1_score = findViewById(R.id.team1score);
        team2_score = findViewById(R.id.team2score);
        increase_team1 = findViewById(R.id.increase_team1);
        increase_team2 = findViewById(R.id.increase_team2);
        decrease_team1 = findViewById(R.id.decrease_team1);
        decrease_team2 = findViewById(R.id.decrease_team2);
        team1_name = findViewById(R.id.team1_name_set);
        team2_name = findViewById(R.id.team2_name_set);
        set_status = findViewById(R.id.set_status);

        team1_name.setText(String.valueOf(txt_team1Name));
        team2_name.setText(String.valueOf(txt_team2Name));



        increase_team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mscore1++;
                team1_score.setText(String.valueOf(mscore1));
                mDatabase.child("matches").child(txt_matchID).child("score").child("score1").setValue(String.valueOf(mscore1));

            }
        });

        increase_team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mscore2++;
                team2_score.setText(String.valueOf(mscore2));
                mDatabase.child("matches").child(txt_matchID).child("score").child("score2").setValue(String.valueOf(mscore2));
            }
        });

        decrease_team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mscore1--;
                team1_score.setText(String.valueOf(mscore1));
                mDatabase.child("matches").child(txt_matchID).child("score").child("score1").setValue(String.valueOf(mscore1));
            }
        });

        decrease_team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mscore2--;
                team2_score.setText(String.valueOf(mscore2));
                mDatabase.child("matches").child(txt_matchID).child("score").child("score2").setValue(String.valueOf(mscore2));
            }
        });

        set_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("matches").child(txt_matchID).child("status").setValue("finished");
                Intent intent = new Intent(LMFootball.this, adminActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt(State_Score_1, mscore1);
        outState.putInt(State_Score_2, mscore2);
        super.onSaveInstanceState(outState);
    }

    private class CreateScore {
        public String score1;
        public String score2;
        public CreateScore(){

        }
        public CreateScore(String score1, String score2) {
            this.score1 = score1;
            this.score2 = score2;
        }
    }

}
