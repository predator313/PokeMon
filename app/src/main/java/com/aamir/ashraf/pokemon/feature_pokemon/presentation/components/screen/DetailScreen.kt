package com.aamir.ashraf.pokemon.feature_pokemon.presentation.components.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aamir.ashraf.pokemon.feature_pokemon.data.mapper.toDetailScreenModel
import com.aamir.ashraf.pokemon.feature_pokemon.presentation.PokeMonViewModel
import com.aamir.ashraf.pokemon.feature_pokemon.presentation.components.PokeMonDetail
import com.aamir.ashraf.pokemon.utils.Resource

@Composable
fun PokeMonDetailScreen(
    id: Int,
    onNavigate: () -> Unit
) {
    val pokeMonViewModel: PokeMonViewModel = hiltViewModel()

    // Fetch the rocket details when the screen is composed
    LaunchedEffect(id) {
        pokeMonViewModel.getPokeMonDetailsById(id)
    }

    val getPokeMonDetailState by pokeMonViewModel.getPokeMonDetailStateById.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Details Of PokeMon $id",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            when (getPokeMonDetailState) {
                is Resource.Loading -> {
                    Text(text = "Loading...")
                }
                is Resource.Success -> {
                    val rocketDetail = (getPokeMonDetailState as Resource.Success).data
                    rocketDetail?.let {
                        PokeMonDetail(it.toDetailScreenModel())
                    }
                }
                is Resource.Error -> {
                    val errorMessage = (getPokeMonDetailState as Resource.Error).message
                    Text(text = "Error: $errorMessage")
                }
            }
        }
    }
}
