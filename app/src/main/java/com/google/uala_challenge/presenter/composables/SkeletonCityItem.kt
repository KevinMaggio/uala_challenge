package com.google.uala_challenge.presenter.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonCityItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SkeletonCircle(size = 48.dp)

        Spacer(modifier = Modifier.padding(8.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            SkeletonBar(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(14.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            SkeletonBar(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(10.dp)
            )
        }

        SkeletonBar(
            modifier = Modifier
                .width(28.dp)
                .height(12.dp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        SkeletonCircle(size = 20.dp)
    }
}
