package com.example.jetpackapp4.P1

fun main() {
    val number = 1
    if (number==1) {
        println("Yes")
    } else {
        println("No")
    }
}

fun Int.toBoolean(): Boolean {
    if (this != 1) {
        return true
    } else {
        return false
    }
}