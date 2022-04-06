package edu.volstate.dicegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

public class HelpSettings extends DialogFragment {
    private View.OnClickListener okButtonOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) { dismiss(); }
    };

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.help_settings, null);
        builder.setView(dialogView);
        Button btnOk = dialogView.findViewById(R.id.btnHelpOk);
        btnOk.setOnClickListener(okButtonOnClickListener);

        return builder.create();
    }
}
