package com.example.imagesearchapp.ui.common.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.imagesearchapp.util.BottomNavType

@Composable
internal fun BottomNavigationItem(
    navigateToRoute: (String) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .navigationBarsPadding()
            .height(56.dp)
            .fillMaxWidth()
    ) {
        var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

        BottomNavType.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(item.bottomTitleRes),
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = stringResource(item.bottomTitleRes),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                label = null,
                selected = selectedIndex == index,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = Color.Transparent
                ),
                onClick = {
                    selectedIndex = index
                    navigateToRoute(item.route)
                }
            )
        }
    }
}