package org.example.kmpdicegame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform