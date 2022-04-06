package edu.volstate.dicegame;

import android.os.Parcel;
import android.os.Parcelable;

public class DiceModel implements Parcelable {
    private int diceMin = 1;
    private int diceMax = 6;
    private int intDieOneScore;
    private int intDieTwoScore;
    private int intDieThreeScore;

    protected DiceModel(Parcel in) {
        diceMin = in.readInt();
        diceMax = in.readInt();
        intDieOneScore = in.readInt();
        intDieTwoScore = in.readInt();
        intDieThreeScore = in.readInt();
    }

    public static final Creator<DiceModel> CREATOR = new Creator<DiceModel>() {
        @Override
        public DiceModel createFromParcel(Parcel in) {
            return new DiceModel(in);
        }

        @Override
        public DiceModel[] newArray(int size) {
            return new DiceModel[size];
        }
    };

    public int getDiceMin() {
        return diceMin;
    }

    public void setDiceMin(int diceMin) {
        this.diceMin = diceMin;
    }

    public int getDiceMax() {
        return diceMax;
    }

    public void setDiceMax(int diceMax) {
        this.diceMax = diceMax;
    }

    public int getIntDieOneScore() {
        return intDieOneScore;
    }

    public void setIntDieOneScore(int intDieOneScore) {
        this.intDieOneScore = intDieOneScore;
    }

    public int getIntDieTwoScore() {
        return intDieTwoScore;
    }

    public void setIntDieTwoScore(int intDieTwoScore) {
        this.intDieTwoScore = intDieTwoScore;
    }

    public int getIntDieThreeScore() {
        return intDieThreeScore;
    }

    public void setIntDieThreeScore(int intDieThreeScore) {
        this.intDieThreeScore = intDieThreeScore;
    }

    public DiceModel(int diceMin, int diceMax, int intDieOneScore, int intDieTwoScore, int intDieThreeScore) {
        this.diceMin = diceMin;
        this.diceMax = diceMax;
        this.intDieOneScore = intDieOneScore;
        this.intDieTwoScore = intDieTwoScore;
        this.intDieThreeScore = intDieThreeScore;
    }

    public DiceModel() {
        this.diceMin = 1;
        this.diceMax = 6;
        this.intDieOneScore = 0;
        this.intDieTwoScore = 0;
        this.intDieThreeScore = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(diceMin);
        parcel.writeInt(diceMax);
        parcel.writeInt(intDieOneScore);
        parcel.writeInt(intDieTwoScore);
        parcel.writeInt(intDieThreeScore);
    }
}
