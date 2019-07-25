package ru.matorinun.upravbotprof.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import javax.inject.Singleton;

@Singleton
@Database(entities = {RequestEntity.class}, version = AppDatabase.VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract RequestDao requestDao();
}
