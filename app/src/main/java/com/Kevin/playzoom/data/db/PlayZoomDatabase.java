package com.Kevin.playzoom.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.Kevin.playzoom.model.ShowEntity;

@Database(entities = {ShowEntity.class}, version = 1)
public abstract class PlayZoomDatabase extends RoomDatabase {
    public abstract ShowDao showDao();

    public static PlayZoomDatabase db;

    public static PlayZoomDatabase getInstance(Context context){
        PlayZoomDatabase dbTemp = db;
        if (dbTemp == null){
            dbTemp = Room.databaseBuilder(context.getApplicationContext(), PlayZoomDatabase.class, "shows-database").build();
            db = dbTemp;
        }
        return  db;
    }
}
