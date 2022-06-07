package com.example.programmingtask.navigation

sealed class Destination(val route: String) {
    object Detail : Destination(route = "detail")
    object Home : Destination(route = "home")
    object List : Destination(route = "list")

    fun withArgs(vararg args: String):String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
