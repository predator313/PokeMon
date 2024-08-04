package com.aamir.ashraf.pokemon.feature_pokemon.presentation.components.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aamir.ashraf.pokemon.feature_pokemon.domain.model.MainScreenModel
import com.aamir.ashraf.pokemon.feature_pokemon.presentation.PokeMonViewModel
import com.aamir.ashraf.pokemon.feature_pokemon.presentation.components.PokeMonList
import com.aamir.ashraf.pokemon.utils.Resource
import com.aamir.ashraf.pokemon.utils.isNetworkAvailable

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
    onItemClick: (MainScreenModel) -> Unit,
) {
    val context =  LocalContext.current

    val pokeMonViewModel: PokeMonViewModel = hiltViewModel()
    val getPokeMonState by pokeMonViewModel.getPokeMonState.collectAsState()
    val pokeMonMainScreenList by pokeMonViewModel.pokeMonMainScreenModelList.collectAsState()

    // Search query implementation
    var searchQuery by remember { mutableStateOf("") }
    val filteredRocketList = pokeMonMainScreenList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Search Bar at the top
                AnimatedVisibility(
                    visible = pokeMonMainScreenList.isNotEmpty(),
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Column {
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Search by PokeMon Name") }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        when (getPokeMonState) {
                            is Resource.Loading<*> -> {
                                Text(text = "Loading...")
                            }
                            is Resource.Success<*> -> {
                                PokeMonList(filteredRocketList, onPokeMonClicked = {
                                    onItemClick(it)
                                    Log.e("clicked", "name ${it.name}")
                                })
                            }
                            is Resource.Error<*> -> {
                                val errorMessage = (getPokeMonState as Resource.Error).message
                                Text(text = "Error: $errorMessage")
                            }
                        }
                    }
                }

                // Button at the center
                AnimatedVisibility(
                    visible = pokeMonMainScreenList.isEmpty(),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Button(onClick = {
                        Log.e("aamir","main screen list ${pokeMonMainScreenList.size}")
                        if(pokeMonMainScreenList.isEmpty() && !isNetworkAvailable(context)){
                            Toast.makeText(context,"please check your internet", Toast.LENGTH_SHORT).show()
                        }
                        pokeMonViewModel.getAllPokeMons() }) {
                        Text(text = "Fetch All PokeMon")
                    }
                }
            }
        }
    )
}