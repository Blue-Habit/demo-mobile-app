/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.task.createTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.sheet.DatePickerBottomSheet
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun CreateTaskScreen(
    uiContract: UIContract<CreateTaskState, CreateTaskAction>
) = UIWrapper(uiContract = uiContract) {

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false,

        )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetContent = {
            DatePickerBottomSheet()
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    elevation = 0.dp,
                    backgroundColor = Color.White,
                    title = {

                        Text(
                            text = stringResource(id = R.string.text_top_bar_create_task),
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.W600,
                            color = Gray900,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_back),
                                contentDescription = stringResource(id = R.string.description_icon_arrow)
                            )
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    )
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }

    }
}

@Preview
@Composable
fun CreateTaskScreenPreview() {
    var state by remember {
        mutableStateOf(CreateTaskState())
    }
    CreateTaskScreen(
        uiContract = UIContract(
            controller = rememberUIController(),
            state = state,
            mutation = {
                state = it
            }
        )
    )
}