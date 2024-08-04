package com.aamir.ashraf.pokemon.feature_pokemon.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.aamir.ashraf.pokemon.feature_pokemon.domain.model.MainScreenModel

@Composable
fun PokeMonList(pokeMons: List<MainScreenModel>,onPokeMonClicked: (MainScreenModel) -> Unit){
    LazyColumn {
        items(pokeMons) { pokeMon ->
            PokeMonCard(pokeMon,onPokeMonClicked)

        }
    }

}