package com.anujdroid.contactapp.ui.screen.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Home : Routes()

    @Serializable
    data object AddContact : Routes()
}
