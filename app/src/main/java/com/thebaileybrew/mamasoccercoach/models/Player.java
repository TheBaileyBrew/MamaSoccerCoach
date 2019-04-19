package com.thebaileybrew.mamasoccercoach.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    private String mPlayerFirstName;
    private String mPlayerLastName;
    private int mPlayerNumber;
    private String mPlayerBestPosition;
    private String mPlayerNotes;

    public Player() {}

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public void setmPlayerFirstName(String mPlayerFirstName) {
        this.mPlayerFirstName = mPlayerFirstName;
    }
    public String getmPlayerFirstName() {
        return mPlayerFirstName;
    }

    public void setmPlayerLastName(String mPlayerLastName) {
        this.mPlayerLastName = mPlayerLastName;
    }
    public String getmPlayerLastName() {
        return mPlayerLastName;
    }

    public void setmPlayerBestPosition(String mPlayerBestPosition) {
        this.mPlayerBestPosition = mPlayerBestPosition;
    }
    public String getmPlayerBestPosition() {
        return mPlayerBestPosition;
    }

    public void setmPlayerNumber(int mPlayerNumber) {
        this.mPlayerNumber = mPlayerNumber;
    }
    public int getmPlayerNumber() {
        return mPlayerNumber;
    }

    public void setmPlayerNotes(String mPlayerNotes) {
        this.mPlayerNotes = mPlayerNotes;
    }
    public String getmPlayerNotes() {
        return mPlayerNotes;
    }

    public Player(String firstName, String lastName, int number, String position, String notes) {
        mPlayerFirstName = firstName;
        mPlayerLastName = lastName;
        mPlayerBestPosition = position;
        mPlayerNumber = number;
        mPlayerNotes = notes;
    }

    protected Player(Parcel in) {
        mPlayerFirstName = in.readString();
        mPlayerLastName = in.readString();
        mPlayerNumber = in.readInt();
        mPlayerBestPosition = in.readString();
        mPlayerNotes = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPlayerFirstName);
        dest.writeString(mPlayerLastName);
        dest.writeInt(mPlayerNumber);
        dest.writeString(mPlayerBestPosition);
        dest.writeString(mPlayerNotes);
    }
}
