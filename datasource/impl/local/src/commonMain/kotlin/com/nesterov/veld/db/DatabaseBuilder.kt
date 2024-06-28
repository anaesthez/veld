package com.nesterov.veld.db

import androidx.room.RoomDatabase

@Suppress("FunctionName")
expect fun DatabaseBuilder(): RoomDatabase.Builder<AppDatabase>