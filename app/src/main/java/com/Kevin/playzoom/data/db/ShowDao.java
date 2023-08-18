package com.Kevin.playzoom.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import com.Kevin.playzoom.model.ShowEntity;

@Dao
public interface ShowDao {
    @Insert
    public void addShow(ShowEntity show);

    @Query("SELECT * FROM show WHERE show_name LIKE :name LIMIT 1")
    public ShowEntity getShowByName(String name);

    @Query("SELECT * FROM show")
    public List<ShowEntity> getAll();

    @Delete
    public void deleteShow(ShowEntity show);
}
