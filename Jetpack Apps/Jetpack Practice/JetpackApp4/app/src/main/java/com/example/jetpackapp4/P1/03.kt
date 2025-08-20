package com.example.jetpackapp4.P1

fun main() {

    // 1. Basic if Statement

    val age = 18
    if (age >= 18) {
        println("You are eligible to vote")
    }

  //   2. if-else Statement

    val number = 15
    if (number % 2 == 0) {
        println("$number is even")
    } else {
        println("$number is odd")
    }

// 3. if-else if-else Chain

    val score = 85
    if (score >= 90) {
        println("Grade: A")
    } else if (score >= 80) {
        println("Grade: B")
    } else if (score >= 70) {
        println("Grade: C")
    } else if (score >= 60) {
        println("Grade: D")
    } else {
        println("Grade: F")
    }

  //  4. if as Expression (Ternary Alternative)

    val temperature = 25
    val weather = if (temperature > 30) "Hot" else "Pleasant"
    println("Weather: $weather")

// Multi-line expression
    val greeting = if (temperature < 10) {
        "Stay warm!"
    } else if (temperature > 30) {
        "Stay cool!"
    } else {
        "Have a great day!"
    }

    println("Greeting: $greeting")

   // 5. when Statement (Switch Alternative)

    val dayOfWeek = 3
    when (dayOfWeek) {
        1 -> println("Monday")
        2 -> println("Tuesday")
        3 -> println("Wednesday")
        4 -> println("Thursday")
        5 -> println("Friday")
        6, 7 -> println("Weekend")
        else -> println("Invalid day")
    }


    // 6. when with Ranges and Conditions

    val marks = 78
    when {
        marks >= 90 -> println("Excellent")
        marks in 80..89 -> println("Very Good")
        marks in 70..79 -> println("Good")
        marks in 60..69 -> println("Average")
        else -> println("Needs Improvement")
    }



}