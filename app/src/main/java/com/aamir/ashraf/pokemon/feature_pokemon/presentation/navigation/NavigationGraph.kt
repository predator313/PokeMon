package com.aamir.ashraf.pokemon.feature_pokemon.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aamir.ashraf.pokemon.feature_pokemon.presentation.components.screen.MainScreen
import com.aamir.ashraf.pokemon.feature_pokemon.presentation.components.screen.PokeMonDetailScreen
import com.aamir.ashraf.pokemon.utils.extractPokemonIdFromUrl

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyNavigationHost(

    navController: NavHostController = rememberNavController()
) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = MainScreenNav) {
            composable<MainScreenNav> {
                MainScreen(

                ){

                    navController.navigate(DetailScreenNav(extractPokemonIdFromUrl(it.url)?:1))
                }
            }
            composable<DetailScreenNav> {
                val detailScreenNav:DetailScreenNav = it.toRoute()
                PokeMonDetailScreen(
                    id = detailScreenNav.id
                ){
                    navController.navigateUp()
                }
            }


        }

    }
}