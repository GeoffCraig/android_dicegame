package edu.volstate.dicegame;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;

public class GestureController extends GestureDetector.SimpleOnGestureListener {
    Context context;
    Activity activity;
    PlayerModel playerModel;
    ArrayList<PlayerModel> playerModelArrayList;
    GameSettingsModel gameSettingsModel;

    public GestureController(Context context, Activity activity, PlayerModel playerModel,
                             ArrayList<PlayerModel> playerModelArrayList, GameSettingsModel gameSettingsModel) {
        this.context = context;
        this.activity = activity;
        this.playerModel = playerModel;
        this.playerModelArrayList = playerModelArrayList;
        this.gameSettingsModel = gameSettingsModel;
    }

    // Developer docs said you always need this even if you are doing down
    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        //courtesy of https://www.tutorialspoint.com/how-to-handle-right-to-left-and-left-to-right-swipe-gestures-on-android
        final int SWIPE_THRESHOLD = 100;
        final int SWIPE_VELOCITY_THRESHOLD = 100;
        try {
            float diffY = event2.getY() - event1.getY();
            float diffX = event2.getX() - event1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                }
            }
            else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeDown();
                    } else {
                        onSwipeUp();
                    }
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        Log.d("Gesture:", "onFling: " + event1.toString() + event2.toString());
        return false;
    }

    private void onSwipeLeft() {
    }

    private void onSwipeRight() {
    }

    private void onSwipeDown() {
        // This is where things start to get tricky
        Button btnScoreBoard;
        btnScoreBoard = activity.findViewById(R.id.btnScoreBoard);
        // See if the user is already there.
            EditText nameEditText;
            nameEditText = activity.findViewById(R.id.editTxtName);
            String userName = nameEditText.getText().toString();
            boolean checkExistingUser = PlayerArrayListController.checkForExistingPlayerByName(playerModelArrayList, userName);
            // If the user isn't already there they are new and we will create a new player.
            if (!checkExistingUser) {
                PlayerModel newPlayerModel = new PlayerModel(userName, 0, 0, 0, true, true, 0);
                playerModelArrayList.add(newPlayerModel);
                playerModel = newPlayerModel;
                PlayerArrayListController.debugPlayerModelArrayList(playerModelArrayList, "GestureController:onSwipeDown");
                PlayerModelController.rollDice(activity, newPlayerModel, gameSettingsModel, playerModelArrayList);
                btnScoreBoard.setEnabled(true);
                Collections.sort(playerModelArrayList);
                SharedPreferencesController.saveSharedPrefs(activity, playerModelArrayList);
                SharedPreferencesController.setCurrentPlayerData(activity, playerModel);
            } else {
                // Need to find the user by name and set the correct playerModel
                PlayerModel currentPlayer = PlayerArrayListController.checkForExistingPlayerReturnPlayer(playerModelArrayList, userName);
                playerModel = currentPlayer;
                PlayerModelController.rollDice(activity, playerModel, gameSettingsModel, playerModelArrayList);
                btnScoreBoard.setEnabled(true);
                Collections.sort(playerModelArrayList);
                SharedPreferencesController.saveSharedPrefs(activity, playerModelArrayList);
                SharedPreferencesController.setCurrentPlayerData(activity, playerModel);
            }
            // ANIMATION ALERT!!
            AnimationController animationController = new AnimationController(context, activity);
            animationController.bounceImage(R.id.imgVDie1, 1000);
            animationController.bounceImage(R.id.imgVDie2, 1000);
            animationController.bounceImage(R.id.imgVDie3, 1000);

    }

    private void onSwipeUp() {
        // MEDIA ALERT!!
        MediaController.playMedia(activity, R.raw.sad_trombone);
    }
}
