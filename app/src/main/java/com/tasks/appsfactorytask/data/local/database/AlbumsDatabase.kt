package com.tasks.appsfactorytask.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tasks.appsfactorytask.data.local.dao.AlbumDao
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class AlbumsDatabase : RoomDatabase() {
    companion object {
        val DATABASE_NAME: String = "AlbumsDataBase"
    }

    abstract fun AlbumDao(): AlbumDao
}