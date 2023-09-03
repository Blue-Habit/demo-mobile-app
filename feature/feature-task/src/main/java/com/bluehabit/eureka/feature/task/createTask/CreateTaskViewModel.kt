/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.task.createTask

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
):MviViewModel<CreateTaskState, CreateTaskAction>(
    CreateTaskState()
) {
    override fun onAction(action: CreateTaskAction) {
        TODO("Not yet implemented")
    }

}