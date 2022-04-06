package edu.volstate.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreBoard extends AppCompatActivity {
    // UI Elements
    private TextView txtViewScoreBoard;
    private TextView txtTotalRolls;
    private Button clearScoresButton;
    private Button returnToGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);
        Bundle bundle;
        bundle = getIntent().getExtras();
        String strPlayerName = bundle.getString("PLAYER_NAME");
        String strTotalRolls = String.valueOf(bundle.getInt("TOTAL_ROLLS"));
        TextView txtThanksForPlaying = findViewById(R.id.txtThanksForPlaying);
        txtThanksForPlaying.setText("Thanks for playing, " + strPlayerName + "!");
        txtTotalRolls = findViewById(R.id.txtTotalDiceRolls);
        txtTotalRolls.setText(strTotalRolls + " Total Dice Rolls:");
        clearScoresButton = findViewById(R.id.btnClearScoreBoard);
        clearScoresButton.setOnClickListener(clearScoresButtonOnClickListener);
        returnToGameButton = findViewById(R.id.btnReturnToGame);
        returnToGameButton.setOnClickListener(returnToGameOnClickListener);
        ScoreBoardController.setScoreBoard(ScoreBoard.this, this);
    }

    // Listeners
    private final View.OnClickListener clearScoresButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { ScoreBoardController.clearScoreBoard(ScoreBoard.this, getBaseContext()); }
    };

    private final View.OnClickListener returnToGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { finish(); }
    };

}
