package edu.volstate.dicegame;

import android.os.Parcel;
import android.os.Parcelable;

public class GameSettingsModel implements Parcelable {
    private boolean isTriple;
    private boolean isDouble;

    protected GameSettingsModel(Parcel in) {
        isTriple = in.readByte() != 0;
        isDouble = in.readByte() != 0;
    }

    public static final Creator<GameSettingsModel> CREATOR = new Creator<GameSettingsModel>() {
        @Override
        public GameSettingsModel createFromParcel(Parcel in) {
            return new GameSettingsModel(in);
        }

        @Override
        public GameSettingsModel[] newArray(int size) {
            return new GameSettingsModel[size];
        }
    };

    public boolean isTriple() {
        return isTriple;
    }

    public void setTriple(boolean triple) {
        isTriple = triple;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    public GameSettingsModel(boolean isTriple, boolean isDouble) {
        this.isTriple = isTriple;
        this.isDouble = isDouble;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (isTriple ? 1 : 0));
        parcel.writeByte((byte) (isDouble ? 1 : 0));
    }
}
