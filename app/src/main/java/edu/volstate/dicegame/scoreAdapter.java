package edu.volstate.dicegame;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class scoreAdapter extends RecyclerView.Adapter<scoreAdapter.MyViewHolder> {
    private ArrayList<PlayerModel> playerModelArrayList;
    private Context context;
    private Activity activity;

    public scoreAdapter(Context context, Activity activity, ArrayList<PlayerModel> playerModelArrayList){
        this.context = context;
        this.activity = activity;
        this.playerModelArrayList = playerModelArrayList;
    }

    @NonNull
    @Override
    public scoreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_rows, parent, false);
        return new scoreAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull scoreAdapter.MyViewHolder holder, int position) {
        holder.txtPlayerName.setText(playerModelArrayList.get(position).getName());
        holder.txtScore.setText("Grand Total: " + String.valueOf(playerModelArrayList.get(position).getTotalScore()));
        int doublesCount = playerModelArrayList.get(position).getTotalDoubles();
        int triplesCount = playerModelArrayList.get(position).getTotalTriples();
        String stats = "Doubles: " + doublesCount + " - Triples: " + triplesCount;
        holder.txtStats.setText(stats);
        // LOOK AT ME MA!  I'M FLASHING!!
        // FOUND THIS HERE: https://mobikul.com/adding-animation-recyclerview-items/
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.flash);
        animation.setDuration(100);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return playerModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtPlayerName, txtScore, txtStats;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPlayerName = itemView.findViewById(R.id.txtViewPlayerName);
            txtScore = itemView.findViewById(R.id.txtViewPlayerScore);
            txtStats = itemView.findViewById(R.id.txtViewStats);
        }
    }
}
