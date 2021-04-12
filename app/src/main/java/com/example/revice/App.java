package com.example.revice;

import android.app.Application;

import androidx.room.Room;

import com.example.revice.ui.room.FillDataBase;
import com.example.revice.ui.sharePrefs.Share;
//синголдон
public class App extends Application {

    public static App instance;
    public static Share share;
    public static FillDataBase fillDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        share = new Share(this);

     fillDataBase = Room.databaseBuilder(this,FillDataBase.class,"database").allowMainThreadQueries().build();
    }
}
