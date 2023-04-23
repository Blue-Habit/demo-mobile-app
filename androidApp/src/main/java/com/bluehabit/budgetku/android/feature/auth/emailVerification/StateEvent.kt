/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.emailVerification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class EmailVerificationState(
    val isLoading: Boolean = true
) : Parcelable

@Immutable
@Parcelize
data class EmailVerificationDataState(
    val a: String = ""
) : Parcelable

sealed interface EmailVerificationEvent {
}