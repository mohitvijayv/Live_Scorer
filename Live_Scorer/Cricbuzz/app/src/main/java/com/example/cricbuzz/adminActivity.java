package com.example.cricbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class adminActivity extends AppCompatActivity {
    private Button createnm;
    private EditText team1Name;
    private EditText team2Name;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        createnm = findViewById(R.id.createNewMatch);
        team1Name = findViewById(R.id.team1_name);
        team2Name = findViewById(R.id.team2_name);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        createnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_team1Name = team1Name.getText().toString();
                String txt_team2Name = team2Name.getText().toString();
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                String matchID = currentDate + currentTime;

                create_newmatch nm = new create_newmatch(matchID, txt_team1Name, txt_team2Name, currentDate, currentTime);
                mDatabase.child("matches").child(matchID).setValue(nm);

                Intent intent = new Intent(adminActivity.this, LMFootball.class);
                Bundle bundle = new Bundle();
                bundle.putString("t1Name", txt_team1Name);
                bundle.putString("t2Name", txt_team2Name);
                bundle.putString("matchID", matchID);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();


            }
        });
    }


}