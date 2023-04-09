/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey700

@Composable
fun ItemTransaction(
    transactionName: String = "",
    transactionAccountName: String = "",
    transactionDate: String = "",
    transactionCategoryName: String = "",
    transactionAmount: String = "",
    transactionType: String = "",
    color: Color = Color.Green,
    clickable: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 20.dp,
                end = 20.dp
            )
            .clickable(
                enabled = clickable,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dummy_food),
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
            Column {
                Text(
                    text = transactionName,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = transactionAccountName,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    color = Grey700
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = transactionDate,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = Grey700
                    )
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Grey300)
                    )
                    Text(
                        text = transactionCategoryName,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = Grey700
                    )

                }

            }

            Column {
                Text(
                    text = transactionAmount,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                    color = color
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = transactionType,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    color = Grey700
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
    }
}

@Preview
@Composable
fun PreviewItemTransaction() {
    BaseMainApp {
        LazyColumn(content = {
            items(3) {
                ItemTransaction(
                    transactionName = "McDonald",
                    transactionAccountName = "Bank BCA",
                    transactionCategoryName = "Makanan",
                    transactionDate = "1 April 2023",
                    transactionAmount = "+Rp90.000",
                    transactionType = "Uang Masuk",
                    color = Color(0xFF57C45C)
                )
            }
        })
    }
}