package edu.volstate.dicegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class AboutSettings extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.about_settings, null);

        builder.setView(dialogView);
        TextView countDownView = dialogView.findViewById(R.id.txtAboutCountdown);
        Log.i("text", countDownView.getText().toString());
        String countDown = getResources().getString(R.string.about_closing_countdown);
        String countDownSuffix = getResources().getString(R.string.about_closing_countdown_seconds);
        new CountDownTimer(20000, 1000) {
            int i = 20;
            @Override
            public void onTick(long l) {
                countDownView.setText(countDown + " " + i + " "+ countDownSuffix);
                i--;
            }

            @Override
            public void onFinish() {
                dismiss();
            }
        }.start();

        return builder.create();
    }
}
