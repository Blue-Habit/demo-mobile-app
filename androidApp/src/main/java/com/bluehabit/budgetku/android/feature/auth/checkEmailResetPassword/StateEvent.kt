/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.checkEmailResetPassword

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CheckEmailResetPasswordState(
    val email: String = "",
) : Parcelable

sealed class CheckEmailResetPasswordEvent {
    object OpenEmailApplication : CheckEmailResetPasswordEvent()
    object ChangeEmailForResetPassword : CheckEmailResetPasswordEvent()
}