package com.example.project1;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;

public class CountryServices{

    private CountriesDatabase db ;

    public CountryServices(Context context){
        db = Room.databaseBuilder(context,
                CountriesDatabase.class, "countries_database").build();
    }





}
