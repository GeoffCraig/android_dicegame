package edu.volstate.dicegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreBoardController {

    public static void clearScoreBoard(Activity activity, Context context) {
        SharedPreferencesController.clearScoreBoardData(activity);
        ArrayList<PlayerModel> playerModelArrayList;
        playerModelArrayList = new ArrayList<>();
        RecyclerView recyclerView = activity.findViewById(R.id.mRecyclerView);
        scoreAdapter adapter = new scoreAdapter(context, activity, playerModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // updateScoreboard();
    }

    public static void openScoreBoard(Activity activity, PlayerModel playerModel) {
        // Grab currentPlayer data from sharedPreferences
        String currentPlayerName = SharedPreferencesController.getCurrentPlayerName(activity);
        int currentPlayerRolls = SharedPreferencesController.getCurrentPlayerRolls(activity);
        Intent scoreBoardIntent = new Intent(activity, ScoreBoard.class);
        scoreBoardIntent.putExtra("PLAYER_NAME", currentPlayerName);
        scoreBoardIntent.putExtra("TOTAL_ROLLS", currentPlayerRolls);
        activity.startActivity(scoreBoardIntent);
    }

    public static void setScoreBoard(Activity activity, Context context) {
        ArrayList<PlayerModel> scoreBoardPlayerArrayList = SharedPreferencesController.getScoreBoardData(activity);
        if(scoreBoardPlayerArrayList == null) {
            scoreBoardPlayerArrayList = new ArrayList<>();
        }
        RecyclerView recyclerView = activity.findViewById(R.id.mRecyclerView);
        scoreAdapter adapter = new scoreAdapter(context, activity, scoreBoardPlayerArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }
}
