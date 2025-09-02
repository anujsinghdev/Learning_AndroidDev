package com.anujdroid.contactapp.modeldatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false  // Changed to false for development
)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun getDao(): ContactDao
}
