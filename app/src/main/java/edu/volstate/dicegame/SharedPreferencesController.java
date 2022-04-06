package edu.volstate.dicegame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferencesController {
    //Using static methods but these really are just void functions
    public static ArrayList<PlayerModel> loadSharedPrefs(Activity activity)
    {
        ArrayList<PlayerModel> playerModelArrayList = new ArrayList<>();
        SharedPreferences sharedPreferences = activity.getSharedPreferences("scoreboard_data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("players", null);
        Type type = new TypeToken<ArrayList<PlayerModel>>() {}.getType();
        playerModelArrayList = gson.fromJson(json, type);
        if(playerModelArrayList == null)
        {
            playerModelArrayList = new ArrayList<>();
            UIController.resetMainScreen(activity);
        }
        return playerModelArrayList;
    }

    public static void saveSharedPrefs(Activity activity, ArrayList<PlayerModel> playerModelArrayList) {
        Log.i("SharedPreferencesController:savedSharedPrefs:ArrayListLength", String.valueOf(playerModelArrayList.size()));
        SharedPreferences sharedPreferences = activity.getSharedPreferences("scoreboard_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String json = gson.toJson(playerModelArrayList);

        editor.putString("players", json);

        editor.commit();
    }

    public static void setCurrentPlayerData(Activity activity, PlayerModel playerModel) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = activity.getSharedPreferences("stats", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(playerModel == null) {
            editor.putInt("total_rolls", 0);
            editor.putString("player_name", "Default");
        } else {
            editor.putInt("total_rolls", playerModel.getCurrentRolls());
            editor.putString("player_name", playerModel.getName());
        }
        editor.commit();
    }

    public static void clearScoreBoardData(Activity activity) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = activity.getSharedPreferences("scoreboard_data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.remove("players");
        editor.commit();
    }
    public static String getCurrentPlayerName(Activity activity) {
        SharedPreferences sharedPreferences;
        sharedPreferences = activity.getSharedPreferences("stats", Context.MODE_PRIVATE);
        return sharedPreferences.getString("player_name", "Default");

    }

    public static int getCurrentPlayerRolls(Activity activity) {
        SharedPreferences sharedPreferences;
        sharedPreferences = activity.getSharedPreferences("stats", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("total_rolls", 0);
    }

    public static ArrayList<PlayerModel> getScoreBoardData(Activity activity) {
        ArrayList<PlayerModel> playerModelArrayList;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("scoreboard_data", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("players", null);
        Type type = new TypeToken<ArrayList<PlayerModel>>() {}.getType();

        playerModelArrayList = gson.fromJson(json, type);
        return playerModelArrayList;
    }
}
