package com.tasks.appsfactorytask.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity

@Dao
interface AlbumDao {

    @Query("SELECT * from Album")
    fun getAllAlbums(): LiveData<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(album: List<AlbumEntity>)

    @Delete
    fun deleteAlbums(album: List<AlbumEntity>)
}
