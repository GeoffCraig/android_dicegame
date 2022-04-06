package edu.volstate.dicegame;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayerModel implements Parcelable, Comparable<PlayerModel> {
    private String name;
    private int totalDoubles;
    private int totalTriples;
    private int totalScore;
    private boolean blnDouble;
    private boolean blnTriple;
    private int currentRolls;

    public PlayerModel(String name, int totalDoubles, int totalTriples,
                       int totalScore, boolean blnDouble,
                       boolean blnTriple, int currentRolls) {
        this.name = name;
        this.totalDoubles = totalDoubles;
        this.totalTriples = totalTriples;
        this.totalScore = totalScore;
        this.blnDouble = blnDouble;
        this.blnTriple = blnTriple;
        this.currentRolls = currentRolls;
    }

    protected PlayerModel(Parcel in) {
        name = in.readString();
        totalDoubles = in.readInt();
        totalTriples = in.readInt();
        totalScore = in.readInt();
        blnDouble = in.readByte() != 0;
        blnTriple = in.readByte() != 0;
        currentRolls = in.readInt();
    }

    public static final Creator<PlayerModel> CREATOR = new Creator<PlayerModel>() {
        @Override
        public PlayerModel createFromParcel(Parcel in) {
            return new PlayerModel(in);
        }

        @Override
        public PlayerModel[] newArray(int size) {
            return new PlayerModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalDoubles() {
        return totalDoubles;
    }

    public void setTotalDoubles(int totalDoubles) {
        this.totalDoubles = totalDoubles;
    }

    public int getTotalTriples() {
        return totalTriples;
    }

    public void setTotalTriples(int totalTriples) {
        this.totalTriples = totalTriples;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public boolean isBlnDouble() {
        return blnDouble;
    }

    public void setBlnDouble(boolean blnDouble) {
        this.blnDouble = blnDouble;
    }

    public boolean isBlnTriple() {
        return blnTriple;
    }

    public void setBlnTriple(boolean blnTriple) {
        this.blnTriple = blnTriple;
    }

    public int getCurrentRolls() {
        return currentRolls;
    }

    public void setCurrentRolls(int currentRolls) {
        this.currentRolls = currentRolls;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(totalDoubles);
        parcel.writeInt(totalTriples);
        parcel.writeInt(totalScore);
        parcel.writeByte((byte) (blnDouble ? 1 : 0));
        parcel.writeByte((byte) (blnTriple ? 1 : 0));
        parcel.writeInt(currentRolls);
    }

    @Override
    public int compareTo(PlayerModel playerModel) {
        if (totalScore == playerModel.totalScore)
            return 0;
        else if (totalScore > playerModel.totalScore)
            return -1;
        else
            return 1;
    }
}
