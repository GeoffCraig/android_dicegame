package edu.volstate.dicegame;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerArrayListController {
    // These are methods to control the arrayList
    public static boolean checkForExistingPlayerByName(ArrayList<PlayerModel> playerModelArrayList, String userName) {
        for (PlayerModel p: playerModelArrayList) {
            if(userName.equals(p.getName())) return true;
        }
        return false;
    }

    public static PlayerModel checkForExistingPlayerReturnPlayer(ArrayList<PlayerModel> playerModelArrayList, String userName ) {
        int i = 0;
        for (PlayerModel p: playerModelArrayList) {
            if(userName.equals(p.getName())) {
                return playerModelArrayList.get(i);
            }
            i++;
        }
        // Should never get here....
        Log.i("PlayerArrayListController:checkforExistingPlayerReturnPlayer", "Didn't find an existing player");
        return null;
    }
    public static void debugPlayerModelArrayList(ArrayList<PlayerModel> playerModelArrayList, String from)
    {
        for (PlayerModel p: playerModelArrayList)
        {
            Log.i(from, p.getName());
        }
    }

}
