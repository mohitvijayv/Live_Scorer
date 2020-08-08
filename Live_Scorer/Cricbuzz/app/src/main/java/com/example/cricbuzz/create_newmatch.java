package com.example.cricbuzz;

public class create_newmatch {
    public String team1name;
    public String team2name;
    public String date;
    public String time;
    public String matchID;

    public create_newmatch(){

    }
    public create_newmatch(String matchID, String team1name, String team2name, String date, String time)
    {
        this.matchID = matchID;
        this.team1name = team1name;
        this.team2name = team2name;
        this.date = date;
        this.time = time;
    }
}
