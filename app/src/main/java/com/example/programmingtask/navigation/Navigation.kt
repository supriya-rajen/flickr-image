package com.example.programmingtask.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.programmingtask.navigation.Destination
import com.example.programmingtask.viewModel.GridImageViewModel
import com.example.programmingtask.viewModel.ListImageViewModel

@ExperimentalFoundationApi
@Composable
fun NavigatePage(
    gridViewModel: GridImageViewModel,
    listViewModel: ListImageViewModel)  {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Destination.Home.route
    ) {

        //  home screen with grid image listing
        composable(route = Destination.Home.route) {
            ImageGridScreen(gridViewModel, itemClick = { route ->
                navHostController.navigate(route)
            })
        }

        // Detail screen when the image is tappped
        composable(Destination.Detail.route +"/{url}/{name}", arguments = listOf(
            navArgument("url"){
                type = NavType.StringType
            },
            navArgument("name"){
                type = NavType.StringType
            }
        )){ entry ->
            val name = entry.arguments?.getString("name")
            val url = entry.arguments?.getString("url")
            url?.let { name?.let { it1 -> ImageDetailCompose(url = it,name = it1) }
            }
        }

        // list view screen with pagination when the load more item is tapped
        composable(route = Destination.List.route) {
            ImageListScreen(listViewModel.user, itemClick = { route ->
                navHostController.navigate(route)
            })
        }
    }

}

