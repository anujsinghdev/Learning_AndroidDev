package com.anujdroid.contactapp.modeldatabase

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact_table ORDER BY firstName ASC")  // ✅ Changed to match Entity
    fun getAllContacts(): Flow<List<Contact>>
}
