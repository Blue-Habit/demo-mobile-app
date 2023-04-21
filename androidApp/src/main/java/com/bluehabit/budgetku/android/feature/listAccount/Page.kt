/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.listAccount

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.feature.listAccount.components.ScreenAccount
import com.bluehabit.budgetku.android.feature.listAccount.components.ScreenSaving
import com.bluehabit.budgetku.android.ui.Grey100
import com.bluehabit.budgetku.android.ui.Grey200
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey500

object ListAccount {
    const val routeName = "ListAccount"

    val tabs = listOf(
        "Semua",
        "Tabungan",
        "Investasi"
    )
}

fun NavGraphBuilder.routeListAccount(
    state: ApplicationState,
) {
    composable(ListAccount.routeName) {
        ScreenListAccount(appState = state)
    }
}

@Composable
internal fun ScreenListAccount(
    appState: ApplicationState,
) = UIWrapper<ListAccountViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colors.surface
                    )
                    .padding(
                        horizontal = 20.dp
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Akun Finansialmu",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Saldo",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Rp6.000.000",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListAccount.tabs.forEachIndexed { index, s ->
                        FilterChip(
                            selected = state.tab == index,
                            enabled = true,
                            onClick = {
                                commit { copy(tab = index) }
                            },
                            colors = ChipDefaults.outlinedFilterChipColors(
                                backgroundColor = Grey200,
                                contentColor = Grey500,
                                selectedBackgroundColor = MaterialTheme.colors.primary,
                                selectedContentColor = MaterialTheme.colors.onPrimary
                            ),
                            border = if (index != state.tab) BorderStroke(
                                width = 1.dp,
                                color = Grey300
                            ) else null,
                        ) {
                            Text(
                                text = s
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            when (state.tab) {
                0 -> ScreenAccount()
                1 -> ScreenSaving()
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {
            ButtonPrimary(text = "Tambah Akun")
        }
    }
}

@Preview
@Composable
fun PreviewScreenListAccount() {
    BaseMainApp {
        ScreenListAccount(it)
    }
}