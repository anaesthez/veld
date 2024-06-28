package com.nesterov.veld.db

import androidx.room.Room
import androidx.room.RoomDatabase
import com.nesterov.veld.di.context.AppContext

@Suppress("FunctionName")
actual fun DatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = AppContext.get()?.applicationContext ?: error("Should be Context but null")
    val dbFile = appContext.getDatabasePath(RoomConstants.DATABASE_NAME)
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}