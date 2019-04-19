package com.thebaileybrew.mamasoccercoach;

import android.app.Application;

public class MamaSoccerCoach extends Application {
    private static MamaSoccerCoach mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static MamaSoccerCoach getContext() {
        return mContext;
    }
}
