package com.list.movie.listmovieapp.data.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movies")
public class MovieEntity implements Serializable {
    @PrimaryKey
    public int id;
    public String title;
    public String overview;
    public String posterPath;
    public float voteAverage;
}
