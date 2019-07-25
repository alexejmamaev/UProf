package ru.matorinun.upravbotprof.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RequestDao {

    @Query("SELECT * FROM RequestEntity ORDER BY id")
    LiveData<List<RequestEntity>> getAll();

    @Query("SELECT * FROM RequestEntity WHERE id =:id")
    LiveData<RequestEntity> getById(int id);

    @Query("SELECT * FROM RequestEntity WHERE status LIKE :status")
    LiveData<List<RequestEntity>> getAllByStatus(String status);

    @Update
    void updateEntities(List<RequestEntity> entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(RequestEntity requestEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<RequestEntity> requestEntityList);

    @Delete
    void delete(RequestEntity requestEntity);

    @Query("SELECT COUNT(*) FROM RequestEntity")
    LiveData<Integer> count();

    @Query("DELETE FROM RequestEntity")
    int nukeTable();

}
