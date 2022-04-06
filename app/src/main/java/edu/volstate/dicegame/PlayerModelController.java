package edu.volstate.dicegame;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

public class PlayerModelController {
    // these are functions we are using to manipulate a player

    // since we need to only show the current rolls of the player's game
    // we are going to reset each of the player's currentRolls to zero in this function
    public static void resetAllCurrentRolls(ArrayList<PlayerModel> playerModelArrayList) {
        for (PlayerModel playerModel : playerModelArrayList) playerModel.setCurrentRolls(0);
    }

    // A player rolls the dice and this happens
    public static void rollDice(Activity activity, PlayerModel playerModel,
                                GameSettingsModel gameSettingsModel,
                                ArrayList<PlayerModel> playerModelArrayList) {
        Log.i("PlayerModelController:rollDice", "starting");
        Log.i("PlayerModelController:rollDice:PlayerModelName", playerModel.getName());
        // Makes the code below easier to read
        int totalTriples = playerModel.getTotalTriples();
        int totalDoubles = playerModel.getTotalDoubles();
        int totalScore = playerModel.getTotalScore();
        int currentRolls = playerModel.getCurrentRolls();
        int totalRoll;
        int updatedTotalScore;

        // Set the player double and triples to the game settings
        playerModel.setBlnDouble(gameSettingsModel.isDouble());
        playerModel.setBlnTriple(gameSettingsModel.isTriple());

        // First thing to do is to get a new dice roll
        DiceModel diceModel = new DiceModel();
        Log.i("PlayerModelController:rollDice:diceMin", String.valueOf(diceModel.getDiceMin()));
        DiceController.rollDice(diceModel);

        // Now we need to see if the player has double or triple set and
        // do stuff accordingly
        if(playerModel.isBlnTriple()) {
            boolean isTriple = DiceController.isTriple(diceModel);
            if(isTriple) {
                Log.i("PlayerModelController:rollDice:rolledTriple", "true");
                playerModel.setTotalTriples(totalTriples + 1);
                totalRoll = DiceController.getRollTotal(diceModel, false, true);
                // Update playerModel
                updatedTotalScore = totalScore + totalRoll;
                playerModel.setTotalScore(updatedTotalScore);
                playerModel.setCurrentRolls(currentRolls + 1);
                SharedPreferencesController.setCurrentPlayerData(activity, playerModel);
                // Update UI
                UIController.updateUIRoll(activity, updatedTotalScore, totalRoll,
                        false, true, diceModel);
                SharedPreferencesController.saveSharedPrefs(activity, playerModelArrayList);
                return;
            }
        }
        if (playerModel.isBlnDouble()) {
            boolean isDouble = DiceController.isDouble(diceModel);
            if(isDouble) {
                Log.i("PlayerModelController:rollDice:rolledDouble", "true");
                playerModel.setTotalDoubles(totalDoubles + 1);
                totalRoll = DiceController.getRollTotal(diceModel, true, false);
                // Update playerModel
                updatedTotalScore = totalScore + totalRoll;
                playerModel.setTotalScore(updatedTotalScore);
                playerModel.setCurrentRolls(currentRolls + 1);
                // Update UI
                UIController.updateUIRoll(activity, updatedTotalScore, totalRoll,
                        true, false, diceModel);
                return;
            }
        }
        Log.i("PlayerModelController:rollDice:noBonuses", "true");
        totalRoll = DiceController.getRollTotal(diceModel, false, false);
        updatedTotalScore = totalScore + totalRoll;
        playerModel.setTotalScore(updatedTotalScore);
        playerModel.setCurrentRolls(currentRolls + 1);
        UIController.updateUIRoll(activity, updatedTotalScore, totalRoll,
                false, false, diceModel);
    }

}
