package com.example.collpage.ui.navigation

sealed class Screen(val route: String) {
    object WelcomePage: Screen("welcome")
    object Login: Screen("login")
    object SignUp: Screen("signUp")
    object Home: Screen("home")
    object SignUp2: Screen("signUp2")
    object ForgotPass: Screen("forgotPass")
    object EmailCheck: Screen("emailCheck")
    object Profile: Screen("profile")
    object SearchPage: Screen("search")
    object Filter: Screen("filter")
    object Message: Screen("message")
    object Job: Screen("job")
}