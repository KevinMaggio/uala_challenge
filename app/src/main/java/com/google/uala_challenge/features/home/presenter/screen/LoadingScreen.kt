package com.google.uala_challenge.features.home.presenter.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.uala_challenge.features.home.presenter.composables.SkeletonBar
import com.google.uala_challenge.features.home.presenter.composables.SkeletonCityItem

@Composable
fun LoadingScreen() {
    Column {
        SkeletonBar(
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
                .width(110.dp)
                .height(30.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(
                count = 10,
                key = { it }
            ) {
                SkeletonCityItem()
            }
        }
    }
}