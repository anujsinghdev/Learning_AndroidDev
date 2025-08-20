package com.example.jetpackapp4.P1

fun main() {
    var name: String? = null ;
    name = null

    print(name?.length)

    square()

}

fun square() {
    val number = 5
    val result = number * number
    println("The square of $number is $result")
}