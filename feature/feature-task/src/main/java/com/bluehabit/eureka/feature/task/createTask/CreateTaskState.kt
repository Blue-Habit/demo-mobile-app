/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.task.createTask

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import javax.annotation.concurrent.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Immutable
@Parcelize
data class CreateTaskState(
    val newName:String = String.Empty,
    val nameError:Boolean = false,

    val imageUrl:String = String.Empty,
    val imageError:Boolean = false,

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = String.Empty,

    override val effect:  @RawValue CreateTaskEffect = CreateTaskEffect.Nothing
) : MviState<CreateTaskEffect>(),Parcelable