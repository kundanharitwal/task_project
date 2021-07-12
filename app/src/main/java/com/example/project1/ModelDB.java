package com.example.project1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ModelDB  {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "capital")
    public String capital;

    @ColumnInfo(name = "region")
    public String region;

    @ColumnInfo(name = "subregion")
    public String subregion;

    @ColumnInfo(name = "population")
    public String population;

    @ColumnInfo(name = "border")
    public String border;

    @ColumnInfo(name = "language")
    public String language;

    @ColumnInfo(name = "flag")
    public String flag;


    public ModelDB(String name, String capital, String region, String subregion, String population, String border, String language, String flag) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.border = border;
        this.language = language;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
