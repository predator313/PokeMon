package com.aamir.ashraf.pokemon.feature_pokemon.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.ashraf.pokemon.feature_pokemon.data.mapper.toMainScreenModel
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDetailsDto
import com.aamir.ashraf.pokemon.feature_pokemon.data.remote.dto.PokeMonDto
import com.aamir.ashraf.pokemon.feature_pokemon.domain.model.MainScreenModel
import com.aamir.ashraf.pokemon.feature_pokemon.domain.use_case.GetPokeMonUseCase
import com.aamir.ashraf.pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokeMonViewModel @Inject constructor(
    private val getPokeMonUseCase: GetPokeMonUseCase
):ViewModel() {
    private val _getPokeMonState = MutableStateFlow<Resource<PokeMonDto>>(Resource.Loading())
    val getPokeMonState: StateFlow<Resource<PokeMonDto>> = _getPokeMonState

    //main screen data
    private val _pokeMonMainScreenModelList = MutableStateFlow<List<MainScreenModel>>(emptyList())
    val pokeMonMainScreenModelList: StateFlow<List<MainScreenModel>> = _pokeMonMainScreenModelList

    fun getAllPokeMons(){
        viewModelScope.launch {
            _getPokeMonState.value = Resource.Loading()
          val result =  withContext(Dispatchers.IO){
                getPokeMonUseCase.getAllPokeMon()
            }
            _getPokeMonState.value = result
            if (result is Resource.Success) {
                //main screen
                _pokeMonMainScreenModelList.value = result.data?.results?.map {
                    it.toMainScreenModel()

                }?: emptyList()
                Log.e("aamir","model list ${pokeMonMainScreenModelList.value.size}")


            }
        }
    }

    //details screen

    private val _getPokeMonDetailStateById = MutableStateFlow<Resource<PokeMonDetailsDto>>(Resource.Loading())
    val getPokeMonDetailStateById:StateFlow<Resource<PokeMonDetailsDto>> = _getPokeMonDetailStateById

    fun getPokeMonDetailsById(id:Int){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getPokeMonUseCase.getPokeMonDetail(id)

            }
            _getPokeMonDetailStateById.value = result
        }
    }


}