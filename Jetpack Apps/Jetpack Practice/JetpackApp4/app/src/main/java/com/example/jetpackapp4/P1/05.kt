package com.example.jetpackapp4.P1

fun main() {
    // Loops in Kotlin

    // For Loop
    for (i in 1..5) {
        println("For Loop: $i")
    }

    // While Loop
    var j = 1
    while (j <= 5) {
        println("While Loop: $j")
        j++
    }

    // Do-While Loop
    var k = 1
    do {
        println("Do-While Loop: $k")
        k++
    } while (k <= 5)

    // Break and Continue
    for (i in 1..10) {
        if (i == 5) {
            break
        }
        println("Break and Continue: $i")
    }


    // Nested Loops
    for (i in 1..3) {
        for (j in 1..3) {
            println("Nested Loops: $i, $j")
        }
    }

    repeat(5) {
        println("Repeat: $it")
    }


    repeat(10) {
        println("Repeat: $it")
    }



}