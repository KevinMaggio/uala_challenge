package com.google.uala_challenge.features.home.presenter.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.uala_challenge.core.utils.toTwoDecimalString
import com.google.uala_challenge.features.home.domain.model.CityModel
import com.google.uala_challenge.ui.theme.Black
import com.google.uala_challenge.ui.theme.Gray

@Composable
fun ItemCity(
    city: CityModel,
    onFavoriteClick: () -> Unit = {},
    onChevronClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .height(48.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
    ) {
        Box {
            InitialCircle(city.name)
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = city.name,
                style = MaterialTheme.typography.labelMedium,
                color = Black,
                fontWeight = FontWeight.Bold
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ubication",
                    tint = Gray,
                    modifier = Modifier.size(12.dp)
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = "Coord: ${city.coordinates.latitude.toTwoDecimalString()}," +
                            " ${city.coordinates.longitude.toTwoDecimalString()}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Gray,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(contentAlignment = Alignment.TopCenter) {
            Text(
                text = city.country,
                style = MaterialTheme.typography.labelSmall,
                color = Gray,
                fontWeight = FontWeight.Black
            )
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onFavoriteClick
            )
        ) {
            Icon(
                imageVector = if (city.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = if (city.isFavorite) "Quitar de favoritos" else "Agregar a favoritos",
                tint = Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onChevronClick
            )
        ) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ver detalle",
                tint = Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
