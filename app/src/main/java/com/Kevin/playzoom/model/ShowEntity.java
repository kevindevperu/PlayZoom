package com.Kevin.playzoom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "show")
public class ShowEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="show_name")
    public String name;

    @ColumnInfo(name="banner")
    public String imageUrl;
}
