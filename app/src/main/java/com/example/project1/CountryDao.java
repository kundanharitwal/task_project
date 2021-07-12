package com.example.project1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CountryDao {

    @Query("select * from modeldb")
    List<ModelDB> getAll();

    @Insert
    void insert(ModelDB task);

    @Delete
    void delete(ModelDB task);

    @Update
    void update(ModelDB task);

    @Query("DELETE FROM modeldb")
    void deleteAllCountries();

}
