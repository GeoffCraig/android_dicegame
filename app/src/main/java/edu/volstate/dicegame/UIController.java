package edu.volstate.dicegame;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UIController {
    // Functions to control the UI, therefore they are static
    public static void resetMainScreen(Activity activity) {
        TextView txtScoreEnhancer = activity.findViewById(R.id.txtScoreEnhancer);
        txtScoreEnhancer.setText("");
        TextView txtScoreThisRound = activity.findViewById(R.id.txtScoreThisRound);
        txtScoreThisRound.setText(R.string.score_this_roll);
        TextView txtScore = activity.findViewById(R.id.txtScore);
        txtScore.setText(R.string.total_score);
    }
    // Set the dice images
    public static void setDiceImage(int dieValue, ImageView die) {
        switch (dieValue) {
            case 1:
                die.setImageResource(R.drawable.die_1);
                break;
            case 2:
                die.setImageResource(R.drawable.die_2);
                break;
            case 3:
                die.setImageResource(R.drawable.die_3);
                break;
            case 4:
                die.setImageResource(R.drawable.die_4);
                break;
            case 5:
                die.setImageResource(R.drawable.die_5);
                break;
            case 6:
                die.setImageResource(R.drawable.die_6);
                break;
        }
    }
    // Update the UI with the new roll data
    public static void updateUIRoll(Activity activity, int totalScore, int roundScore,
                                    boolean isDouble, boolean isTriple, DiceModel diceModel) {
        // Debugging
        Log.i("UIController:updateUIRoll:Die1", String.valueOf(diceModel.getIntDieOneScore()));
        Log.i("UIController:updateUIRoll:TotalScore", String.valueOf(totalScore));
        Log.i("UIController:updateUIRoll:RoundScore", String.valueOf(roundScore));

        // Get the UI components
        TextView txtScoreEnhancer, txtTotalScore, txtScoreThisRound;
        txtScoreEnhancer = activity.findViewById(R.id.txtScoreEnhancer);
        txtTotalScore = activity.findViewById(R.id.txtScore);
        txtScoreThisRound = activity.findViewById(R.id.txtScoreThisRound);

        if (isTriple) {
            txtScoreEnhancer.setText("Triple! + 100");
        } else if (isDouble) {
            txtScoreEnhancer.setText("Double! + 50");
        } else {
            txtScoreEnhancer.setText("No Score Enhancer");
        }
        txtTotalScore.setText("Score: " + totalScore);
        txtScoreThisRound.setText("Score this roll: " + roundScore);
        // MEDIA ALERT!!!
        MediaController.playMedia(activity, R.raw.zapsplat_leisure_board_game_yahtzee_dice_x1_put_in_shaker);
        setDiceImage(diceModel.getIntDieOneScore(), activity.findViewById(R.id.imgVDie1));
        setDiceImage(diceModel.getIntDieTwoScore(), activity.findViewById(R.id.imgVDie2));
        setDiceImage(diceModel.getIntDieThreeScore(), activity.findViewById(R.id.imgVDie3));

    }
    // Validate there is data in the EditText
    public static boolean checkEditText(Activity activity, Context context) {
        EditText nameEditText;
        nameEditText = activity.findViewById(R.id.editTxtName);
        if(TextUtils.isEmpty(nameEditText.getText().toString())) {
            Toast.makeText(activity, "Please enter your name", Toast.LENGTH_LONG).show();
            nameEditText.requestFocus();
            AnimationController animationController = new AnimationController(context, activity);
            animationController.bounceEditText(R.id.editTxtName, 1000);
            return false;
        }
        return true;
    }
}
