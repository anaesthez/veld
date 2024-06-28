package com.nesterov.veld.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.nesterov.veld.common.AppDispatcher
import com.nesterov.veld.creature.dao.CreatureDAO
import com.nesterov.veld.creature.entity.CreatureRoomEntity

@Database(entities = [CreatureRoomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCreatureDao(): CreatureDAO
}

fun AppDatabase(
    builder: RoomDatabase.Builder<AppDatabase> = DatabaseBuilder(),
    appDispatcher: AppDispatcher,
): AppDatabase {
    return builder
        //.addMigrations()
        //.fallbackToDestructiveMigrationOnDowngrade()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(appDispatcher.ioDispatcher)
        .build()
}