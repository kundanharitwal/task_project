package com.example.project1;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ModelDB.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();
}
