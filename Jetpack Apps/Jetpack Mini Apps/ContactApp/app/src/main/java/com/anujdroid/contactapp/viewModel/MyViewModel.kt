package com.anujdroid.contactapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anujdroid.contactapp.modeldatabase.Contact
import com.anujdroid.contactapp.modeldatabase.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val database: ContactDatabase
)
    : ViewModel() {

        private val _getContacts = MutableStateFlow<List<Contact>>(emptyList())
        val getContacts = _getContacts.asStateFlow()

    fun getAllContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            database.getDao().getAllContacts().collect {
                _getContacts.value = it
            }
        }

    }

    fun addContact(name: String, lastName: String, phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            database.getDao().upsertContact(
                Contact(
                    firstName = name,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )
            )
        }

    }

}