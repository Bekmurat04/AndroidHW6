package com.example.revice.ui.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.revice.ui.home.recycler.HomeModel;
//з
@Database(entities = {HomeModel.class},version = 1)
public abstract class FillDataBase extends RoomDatabase {
    public abstract FillDao fillDao();


}
