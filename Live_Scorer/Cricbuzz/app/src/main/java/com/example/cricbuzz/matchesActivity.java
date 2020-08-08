package com.example.cricbuzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class matchesActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    private int match_count;
    private LayoutInflater l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        l = getLayoutInflater();
        final ViewGroup parent =(ViewGroup)findViewById(R.id.insert_point);
        LinearLayout ins = (LinearLayout)findViewById(R.id.insert_point);
        // ins.addView(sub, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //View sub1 = l.inflate(R.layout.sublayout, parent, false);
        //ins.addView(sub1);
        mDatabase = database.getReference();
        mDatabase.child("matches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                match_count = (int) snapshot.getChildrenCount();
                //TextView tv = (TextView) sub.findViewById(R.id.status);
                //tv.setText(String.valueOf(match_count));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("matches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                parent.removeAllViews();
                for(DataSnapshot snap: snapshot.getChildren()){
                    String matchId = snap.getKey();
                    View sub = l.inflate(R.layout.sublayout, parent, false);
                    String date = snap.child("date").getValue(String.class);
                    TextView td = (TextView) sub.findViewById(R.id.date);
                    td.setText(String.valueOf(date));
                    String status = snap.child("status").getValue(String.class);
                    TextView ts = (TextView) sub.findViewById(R.id.status);
                    ts.setText(String.valueOf(status));
                    String team1_name = snap.child("team1name").getValue(String.class);
                    TextView tn1 = (TextView) sub.findViewById(R.id.team1_name_dv);
                    tn1.setText(String.valueOf(team1_name));
                    String team2_name = snap.child("team2name").getValue(String.class);
                    TextView tn2 = (TextView) sub.findViewById(R.id.team2_name_dv);
                    tn2.setText(String.valueOf(team2_name));
                    String team1_score = snap.child("score").child("score1").getValue(String.class);
                    TextView ts1 = (TextView) sub.findViewById(R.id.team1_score_dv);
                    ts1.setText(String.valueOf(team1_score));
                    String team2_score = snap.child("score").child("score2").getValue(String.class);
                    TextView ts2 = (TextView) sub.findViewById(R.id.team2_score_dv);
                    ts2.setText(String.valueOf(team2_score));


                    parent.addView(sub);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //for (int i=0; i<match_count; i++)
        //{
        //
        //    String matchID = mDatabase.child("matches").getKey();
        //
        //}








    }
}