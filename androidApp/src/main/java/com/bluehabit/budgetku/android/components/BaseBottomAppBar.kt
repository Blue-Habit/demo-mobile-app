/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package com.bluehabit.budgetku.android.components

import androidx.compose.runtime.Composable
import com.bluehabit.budgetku.android.ApplicationState

@Composable
fun BaseBottomAppBar(
    state:ApplicationState
) {
    with(state){
        when(state.bottomAppBarType){
            else ->Unit
        }
    }
}