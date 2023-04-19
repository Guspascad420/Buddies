package com.example.collpage.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.collpage.ui.AuthViewModel
import com.example.collpage.ui.Job
import com.example.collpage.ui.MainViewModel
import com.example.collpage.ui.SearchViewModel
import com.example.collpage.ui.screens.*
import com.example.collpage.ui.screens.user_fields.educations.AddEducation
import com.example.collpage.ui.screens.user_fields.educations.EducationScreen
import com.example.collpage.ui.screens.user_fields.experiences.AddExperience
import com.example.collpage.ui.screens.user_fields.experiences.ExperienceScreen
import com.example.collpage.ui.screens.user_fields.project.AddProject
import com.example.collpage.ui.screens.user_fields.project.ProjectScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun CollpageApp(navController: NavHostController = rememberNavController()) {
    AppNavHost(navController)
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val auth: FirebaseAuth = Firebase.auth
    val startDestination = if (auth.currentUser != null) Screen.Home.route
    else Screen.WelcomePage.route

    val mainViewModel: MainViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.WelcomePage.route) {
            WelcomePage(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginPage(
                navigateToSignUp = { navController.navigate(Screen.SignUp.route) },
                navigateToHome = { navController.navigate(Screen.Home.route) },
                navigateToForgotPass = { navController.navigate(Screen.ForgotPass.route) }
            )
        }
        composable(Screen.SignUp.route) { backStackEntry ->
            val viewModel: AuthViewModel = viewModel(backStackEntry)
            SignUpPage(viewModel) { navController.navigate(Screen.SignUp2.route) }
        }
        composable(Screen.SignUp2.route) {
            val viewModel: AuthViewModel = viewModel(navController.previousBackStackEntry!!)
            SignUpPage2(viewModel) { navController.navigate(Screen.Home.route) }
        }
        composable(Screen.Home.route) {
            HomeScreen(
                {
                    navController.navigate(Screen.WelcomePage.route) {
                        popUpTo(Screen.WelcomePage.route)
                    }
                },
                {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.Home.route)
                    }
                }, { navController.navigate(Screen.SearchPage.route) },
                {
                    navController.navigate(Screen.Message.route) {
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                mainViewModel
            )
        }
        composable(Screen.ForgotPass.route) {
            ForgotPassword { navController.navigate(Screen.EmailCheck.route) }
        }
        composable(Screen.EmailCheck.route) {
            EmailCheck { navController.navigate(Screen.Login.route) }
        }
        composable(Screen.SearchPage.route) {
            SearchPage(
                searchViewModel, {
                    navController.navigate(Screen.Filter.route)
                }, { jobId ->
                    navController.navigate(Screen.Job.route + "/$jobId")
                }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(mainViewModel, navController)
        }
        composable(Screen.Profile.route + "/projects") {
            ProjectScreen(mainViewModel, { navController.popBackStack() }) {
                navController.navigate(Screen.Profile.route + "/projects/add")
            }
        }
        composable(Screen.Profile.route + "/projects/add") {
            AddProject(mainViewModel) { navController.popBackStack() }
        }
        composable(Screen.Profile.route + "/educations") {
            EducationScreen(mainViewModel) {
                navController.navigate(Screen.Profile.route + "/educations/add")
            }
        }
        composable(Screen.Profile.route + "/educations/add") {
            AddEducation(mainViewModel)
        }
        composable(Screen.Profile.route + "/experiences") {
            ExperienceScreen(mainViewModel, { navController.popBackStack() }) {
                navController.navigate(Screen.Profile.route + "/experiences/add")
            }
        }
        composable(Screen.Profile.route + "/experiences/add") {
            AddExperience(mainViewModel) { navController.popBackStack() }
        }
        composable(Screen.Filter.route) {
            FilterScreen()
        }
        composable(Screen.Profile.route + "/edit") {
            EditProfile(mainViewModel)
        }
        composable(Screen.Message.route) {
            MessagePage(
                mainViewModel,
                {
                    navController.navigate(Screen.Message.route) {
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
        composable(Screen.Job.route + "/{jobId}") {
            val jobId = it.arguments?.getString("jobId")
            val job: Job = searchViewModel.jobsList.filter { job -> job.id == jobId }[0]
            JobDetailsScreen(job)
        }
    }
}