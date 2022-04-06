package edu.volstate.dicegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Models
    PlayerModel playerModel;
    DiceModel diceModel;
    GameSettingsModel gameSettingsModel = new GameSettingsModel(true, true);
    // UI Objects
    private Button scoreBoardButton;
    private EditText nameEditText;
    private FloatingActionButton helpButton;
    // Other
    private AnimationController animationController;
    private GestureDetectorCompat gestureDetectorCompat;
    // Who is playing?
    public ArrayList<PlayerModel> playerModelArrayList = new ArrayList<>();
    String currentPlayer = "";
    int totalRolls = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setting up all the UI Components
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nameEditText = findViewById(R.id.editTxtName);
        scoreBoardButton = findViewById(R.id.btnScoreBoard);
        scoreBoardButton.setOnClickListener(scoreBoardButtonOnClickListener);
        helpButton = findViewById(R.id.helpButton);
        GestureController gestureController = new GestureController(MainActivity.this, this, playerModel,
                playerModelArrayList, gameSettingsModel);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureController);
        // ANIMATION ALERT!!!
        animationController = new AnimationController(this, MainActivity.this);
        animationController.bounceFloatingButton(R.id.helpButton, 3000);
        helpButton.setOnClickListener(helpButtonOnClickListener);
        // Need to populate the playerModelArrayList from SharedPrefs
        SharedPreferencesController.loadSharedPrefs(MainActivity.this);
        // Since we are creating we need to set each player's current rolls to 0
        PlayerModelController.resetAllCurrentRolls(playerModelArrayList);
        SharedPreferencesController.setCurrentPlayerData(MainActivity.this, this.playerModel);

        // Lifecycle Logging
        Log.i("MainActivity:onCreate", "Look at me, I was created!");
    }
    // Listeners
    private View.OnClickListener scoreBoardButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PlayerArrayListController.debugPlayerModelArrayList(playerModelArrayList, "MainActivity:scoreBoardButtonOnClickListener");
            Log.i("MainActivity:scoreBoardButtonOnClickListener:currentPlayer",
                    currentPlayer + ": " + String.valueOf(totalRolls));
            ScoreBoardController.openScoreBoard(MainActivity.this, playerModel);
        }
    };

    private View.OnClickListener helpButtonOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            HelpSettings helpSettings = new HelpSettings();
            helpSettings.show(getSupportFragmentManager(), "help_settings");
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //Create dialog objects
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dialog_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.about_settings:
                AboutSettings aboutSettings = new AboutSettings();
                aboutSettings.show(getSupportFragmentManager(), "about_settings");
                return true;
            case R.id.dialog_settings:

                GameSettings gameSettings = new GameSettings();

                gameSettings.sendGameSettingsModel(gameSettingsModel);

                gameSettings.show(getSupportFragmentManager(), "game_settings");
                return true;
            case R.id.help_settings:
                HelpSettings helpSettings = new HelpSettings();
                helpSettings.show(getSupportFragmentManager(), "help_settings");
            //Toast.makeText(this, "Dialog Settings will go here!", Toast.LENGTH_SHORT).show();
            /*case R.id.ludicrous_settings:
                LudicrousSettings ludicrousSettings = new LudicrousSettings();
                ludicrousSettings.sendLuda(luda);
                ludicrousSettings.show(getSupportFragmentManager(), "ludicrous_settings");*/
        }
        return super.onOptionsItemSelected(item);
    }


    // Getting playerModel from GameSettings
    public void getGameSettingsModel(GameSettingsModel gameSettingsModel) {
        this.gameSettingsModel = gameSettingsModel;
    }

    // Lifecycle Methods
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelable("dice_data", diceModel);
        outState.putParcelable("player", playerModel);
        outState.putParcelable("game_settings", gameSettingsModel);
        // Lifecycle logging
        Log.i("MainActivity:onSaveInstanceState", "Look at me, I was saved!");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.diceModel = savedInstanceState.getParcelable("dice_data");
        this.playerModel = savedInstanceState.getParcelable("player");
        this.gameSettingsModel = savedInstanceState.getParcelable("game_settings");
        playerModelArrayList = SharedPreferencesController.loadSharedPrefs(MainActivity.this);
        // Lifecycle logging
        Log.i("MainActivity:onRestoreInstanceState", "Look at me, I was restored!");
    }

    @Override
    public void onResume() {
        super.onResume();
        playerModelArrayList = SharedPreferencesController.loadSharedPrefs(MainActivity.this);
    }
}