package edu.volstate.dicegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.DialogFragment;

public class GameSettings extends DialogFragment {
    // private DiceModel sentDice;
    private GameSettingsModel gameSettingsModel;
    private View.OnClickListener cancelButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { dismiss(); }
    };

    private View.OnClickListener saveButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity callingActivity = (MainActivity) getActivity();
            // callingActivity.getDice(sentDice);
            callingActivity.getGameSettingsModel(gameSettingsModel);
            dismiss(); }
    };

    private CompoundButton.OnCheckedChangeListener switchIsDoubleOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(isChecked)
            {
                gameSettingsModel.setDouble(true);
            }
            else
            {
                gameSettingsModel.setDouble(false);
            }

        }
    };

    private CompoundButton.OnCheckedChangeListener switchIsTripleOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(isChecked)
            {
                gameSettingsModel.setTriple(true);
            }
            else {
                gameSettingsModel.setTriple(false);
            }
        }
    };


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.game_settings, null);

        //builder.setView(dialogView).setMessage("Game Settings");
        builder.setView(dialogView);

        Button btnSave = dialogView.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(saveButtonOnClickListener);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(cancelButtonOnClickListener);
        Switch switchIsDouble = dialogView.findViewById(R.id.switchLudicrous);
        if (gameSettingsModel.isDouble()) {
            switchIsDouble.setChecked(true);
        } else {
            switchIsDouble.setChecked(false);
        }
        switchIsDouble.setOnCheckedChangeListener(switchIsDoubleOnCheckedChangeListener);
        Switch switchIsTriple = dialogView.findViewById(R.id.switchEnableTriple);
        if (gameSettingsModel.isTriple()) {
            switchIsTriple.setChecked(true);
        } else {
            switchIsTriple.setChecked(false);
        }
        switchIsTriple.setOnCheckedChangeListener(switchIsTripleOnCheckedChangeListener);

        return builder.create();
    }

    public void sendGameSettingsModel(GameSettingsModel gameSettingsModel) {
        this.gameSettingsModel = gameSettingsModel;
        Log.i("GameSettings:sendPlayerModel:", "Triple " + this.gameSettingsModel.isTriple());
        Log.i("GameSettings:sendPlayerModel:", "Double " + this.gameSettingsModel.isDouble());
        }
}
