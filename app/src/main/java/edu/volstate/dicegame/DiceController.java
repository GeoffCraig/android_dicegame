package edu.volstate.dicegame;

import android.util.Log;

import java.util.Random;

public class DiceController {

    public static void rollDice(DiceModel diceModel) {
        Log.i("DiceController:rollDice:status", "They see me rolling");
        Log.i("DiceController:rollDice:die1", String.valueOf(diceModel.getIntDieOneScore()));
        int diceMin = diceModel.getDiceMin();
        int diceMax = diceModel.getDiceMax();
        diceModel.setIntDieOneScore(randomNumber(diceMin, diceMax));
        diceModel.setIntDieTwoScore(randomNumber(diceMin, diceMax));
        diceModel.setIntDieThreeScore(randomNumber(diceMin, diceMax));
        Log.i("DiceController:rollDice:die1", String.valueOf(diceModel.getIntDieOneScore()));
    }
    private static int randomNumber(int diceMin, int diceMax) {
        if (diceMin >= diceMax) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((diceMax - diceMin) + 1) + diceMin;
    }

    public static boolean isDouble(DiceModel diceModel) {
        return diceModel.getIntDieOneScore() == diceModel.getIntDieTwoScore() ||
                diceModel.getIntDieOneScore() == diceModel.getIntDieThreeScore() ||
                diceModel.getIntDieTwoScore() == diceModel.getIntDieThreeScore();
    }

    public static boolean isTriple(DiceModel diceModel) {
        return diceModel.getIntDieOneScore() == diceModel.getIntDieTwoScore() &&
                diceModel.getIntDieTwoScore() == diceModel.getIntDieThreeScore();
    }
    // This just calculates the roll total
    public static int getRollTotal(DiceModel diceModel, boolean isDouble, boolean isTriple) {
        // Setting these to make the code easier to read
        int dieOne = diceModel.getIntDieOneScore();
        int dieTwo = diceModel.getIntDieTwoScore();
        int dieThree = diceModel.getIntDieThreeScore();
        int totalRoll;
        totalRoll = dieOne + dieTwo + dieThree;
        //Add in the bonuses!
        if(isTriple) {
            totalRoll += 100;
            return totalRoll;
        } else if (isDouble) {
            totalRoll += 50;
            return totalRoll;
        } else {
            return totalRoll;
        }
    }
}
