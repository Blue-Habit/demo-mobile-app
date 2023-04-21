/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createBudget

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CreateBudgetState(
    val a: String = ""
) : Parcelable

@Immutable
@Parcelize
data class CreateBudgetDataState(
    val a: String = ""
) : Parcelable

sealed class CreateBudgetEvent {
}