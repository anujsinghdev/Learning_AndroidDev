package com.example.jetpackapp4.P1

fun main() {

   //  7. when as Expression

    val marks = 55
    val grade = when (marks) {
        in 90..100 -> "A+"
        in 80..89 -> "A"
        in 70..79 -> "B"
        in 60..69 -> "C"
        else -> "F"
    }
    println("Grade: $grade")


 //   8. Nested Conditions

    val userAge = 25
    val hasLicense = true

    if (userAge >= 18) {
        if (hasLicense) {
            println("Can drive")
        } else {
            println("Need to get license first")
        }
    } else {
        println("Too young to drive")
    }


  //   9. Boolean Operators in Conditions

    val username = "admin"
    val password = "12345"

    if (username == "admin" && password == "12345") {
        println("Login successful")
    } else {
        println("Invalid credentials")
    }

// OR condition
    val dayOfWeek = null
    val isWeekend = dayOfWeek == 6 || dayOfWeek == 7
    if (isWeekend) {
        println("Enjoy your weekend!")
    }


   //  10. Checking Null Values


    val name: String? = null

    if (name != null) {
        println("Hello, $name")
    } else {
        println("Name is null")
    }


}