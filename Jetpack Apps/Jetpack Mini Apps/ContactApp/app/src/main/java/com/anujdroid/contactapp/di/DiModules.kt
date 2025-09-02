package com.anujdroid.contactapp.di

import android.app.Application
import androidx.room.Room
import com.anujdroid.contactapp.modeldatabase.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModules {

    @Provides
    @Singleton
    fun providesDatabase(app: Application): ContactDatabase {
        return Room.databaseBuilder(
            app,
            ContactDatabase::class.java,
            "contacts_database"
        ).build()
    }
}
