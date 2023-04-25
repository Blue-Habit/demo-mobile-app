/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.listTransaction

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.transaction.TransactionModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ListTransactionState(
    val a: String = ""
) : Parcelable

@Immutable
@Parcelize
data class ListTransactionDataState(
    val balance: BigDecimal = BigDecimal(2_000_000),
    val income:BigDecimal = BigDecimal(1_000_000),
    val expenses:BigDecimal = BigDecimal(8_000_000),
    val transactions: @RawValue List<TransactionModel> = listOf(
        TransactionModel(
            transactionName = "McDonald",
            isTransactionExpenses = false,
            transactionAmount = BigDecimal(90_000),
            transactionDate = LocalDate.now(),
            transactionAccountName = "Bank BCA",
            transactionCategory = "Makanan"
        ),
        TransactionModel(
            transactionName = "Listrik",
            isTransactionExpenses = true,
            transactionAmount = BigDecimal(100_000),
            transactionDate = LocalDate.now(),
            transactionAccountName = "Bank Jago",
            transactionCategory = "Utilitas"
        ),
        TransactionModel(
            transactionName = "KFC",
            isTransactionExpenses = false,
            transactionAmount = BigDecimal(10_000),
            transactionDate = LocalDate.now(),
            transactionAccountName = "Bank Jago",
            transactionCategory = "Makanan"
        )
    ),
) : Parcelable

sealed interface ListTransactionEvent {
}