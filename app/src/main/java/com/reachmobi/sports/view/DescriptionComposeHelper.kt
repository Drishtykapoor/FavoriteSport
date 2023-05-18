package com.reachmobi.sports.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import javax.inject.Inject

class DescriptionComposeHelper @Inject constructor(){

    @Composable
    fun successCase(title: String, description: String) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = description,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }

    @Composable
    fun errorCase(error: String){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = error,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }

}