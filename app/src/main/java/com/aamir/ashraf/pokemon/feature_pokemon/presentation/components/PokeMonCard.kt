package com.aamir.ashraf.pokemon.feature_pokemon.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aamir.ashraf.pokemon.feature_pokemon.domain.model.DetailScreenModel
import com.aamir.ashraf.pokemon.feature_pokemon.domain.model.MainScreenModel

@Composable
fun PokeMonCard(mainScreenModel: MainScreenModel, onPokeMonClicked:(MainScreenModel)->Unit){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onPokeMonClicked(mainScreenModel) }
        ,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${mainScreenModel.name}", style = MaterialTheme.typography.headlineSmall)
            Text(text = "url: ${mainScreenModel.url}", style = MaterialTheme.typography.bodyLarge)

        }
    }

}

@Composable
fun PokeMonDetailCard(detailScreenModel: DetailScreenModel){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${detailScreenModel.name}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Id: ${detailScreenModel.id}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "BaseExperience: ${detailScreenModel.baseExperience}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Height: ${detailScreenModel.height}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "isDefault: ${detailScreenModel.isDefault}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Weight: ${detailScreenModel.weight}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Order: ${detailScreenModel.order}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ability: ${detailScreenModel.abilities}", style = MaterialTheme.typography.bodyLarge)

        }
    }

}