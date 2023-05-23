/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.report


import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ChipDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.bottomNavigationListener
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.base.listener.BottomNavigationListener
import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetAddBudgetTransaction
import com.bluehabit.budgetku.android.feature.createAccount.CreateAccount
import com.bluehabit.budgetku.android.feature.createBudget.CreateBudget
import com.bluehabit.budgetku.android.feature.createTransaction.CreateTransaction
import com.bluehabit.budgetku.android.ui.Grey200
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey500
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


object Report {
    const val routeName = "Report"
}

fun NavGraphBuilder.routeReport(
    state: ApplicationState,
) {
    composable(Report.routeName) {
        ScreenReport(appState = state)
    }
}

@Composable
internal fun ScreenReport(
    appState: ApplicationState,
) = UIWrapper<ReportViewModel>(appState = appState) {
    with(appState) {
        setupTopAppBar {
            TopAppBarDashboardReport()
        }
        setupBottomSheet {
            BottomSheetAddBudgetTransaction(
                onDismiss = {
                    hideBottomSheet()
                },
                onAddAccount = {
                    hideBottomSheet()
                    navigateSingleTop(CreateAccount.routeName)
                },
                onAddTransaction = {
                    hideBottomSheet()
                    navigateSingleTop(CreateTransaction.routeName)
                },
                onAddBudget = {
                    hideBottomSheet()
                    navigateSingleTop(CreateBudget.routeName)
                },
                onAddTransfer = {},
            )
        }
        bottomNavigationListener(object : BottomNavigationListener {
            override fun onRefresh(item: DashboardBottomNavigationMenu) {
                // remove empty

            }

            override fun onNavigate(item: DashboardBottomNavigationMenu) {
                navigateSingleTop(item.route)
            }

            override fun onFab() {
                showBottomSheet()
            }

        })
    }

    LazyColumn(content = {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Bagaimana alokasi Keuanganmu?",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(
                        horizontal = 20.dp
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        FilterChip(
                            selected = true,
                            enabled = true,
                            onClick = {
                            },
                            colors = ChipDefaults.outlinedFilterChipColors(
                                backgroundColor = Grey200,
                                contentColor = Grey500,
                                selectedBackgroundColor = MaterialTheme.colors.primary,
                                selectedContentColor = MaterialTheme.colors.onPrimary
                            )
                        ) {
                            Text(
                                text = "Pemasukkan"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        FilterChip(
                            selected = false,
                            enabled = true,
                            onClick = {
                            },
                            colors = ChipDefaults.outlinedFilterChipColors(
                                backgroundColor = Grey200,
                                contentColor = Grey500,
                                selectedBackgroundColor = MaterialTheme.colors.primary,
                                selectedContentColor = MaterialTheme.colors.onPrimary
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = Grey300
                            )
                        ) {
                            Text(
                                text = "Pemasukkan"
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = ""
                    )
                }
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp),
                    factory = {
                        PieChart(it).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                // on below line we are specifying layout
                                // params as MATCH PARENT for height and width.
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )
                            // on below line we are setting description
                            // enables for our pie chart.
                            this.description.isEnabled = false

                            // on below line we are setting draw hole
                            // to false not to draw hole in pie chart
                            this.isDrawHoleEnabled = true

                            // on below line we are enabling legend.
                            this.legend.isEnabled = true

                            // on below line we are specifying
                            // text size for our legend.
                            this.legend.textSize = 14F

                            // on below line we are specifying
                            // alignment for our legend.
                            this.legend.horizontalAlignment =
                                Legend.LegendHorizontalAlignment.CENTER


                        }

                    },
                    update = {
                        val pieEntries = mutableListOf<PieEntry>()

                        //init data
                        val typeAmountMap: MutableMap<String, Int> = HashMap()
                        typeAmountMap["Makan"] = 150
                        typeAmountMap["Tagihan"] = 90
                        typeAmountMap["Transportasi"] = 300

                        //init color
                        val colors = ArrayList<Int>()
                        colors.add(Color.parseColor("#35DC86"))
                        colors.add(Color.parseColor("#48ACFF"))
                        colors.add(Color.parseColor("#FECC33"))

                        //input data and fit into pieChart
                        for (type in typeAmountMap.keys) {
                            pieEntries.add(PieEntry(typeAmountMap[type]?.toFloat() ?: 0f, type))
                        }

                        //collecting the entries with label name
                        //collecting the entries with label name
                        val pieDataSet = PieDataSet(pieEntries, "")
                        //setting text size of the value
                        //setting text size of the value
                        pieDataSet.valueTextSize = 12f
                        //providing color list for coloring different entries
                        //providing color list for coloring different entries
                        pieDataSet.colors = colors
                        //grouping the data set from entry to chart
                        //grouping the data set from entry to chart
                        val pieData = PieData(pieDataSet)
                        //showing the value of the entries, default true if not set
                        //showing the value of the entries, default true if not set
                        pieData.setDrawValues(true)

                        it.data = pieData
                        it.centerText = "Rp1.800.00";
                        it.setCenterTextSize(14f);
                        it.setCenterTextColor(Color.BLUE);
                        it.invalidate()
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
        item {
            Text(
                text = "Profil Arus Kas Kamu",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(
                    horizontal = 20.dp
                )
            )
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                factory = {
                    BarChart(it).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            // on below line we are specifying layout
                            // params as MATCH PARENT for height and width.
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                        )
                        axisRight.apply {
                            setDrawGridLines(false)
                            setDrawAxisLine(false)
                            setDrawLabels(false)

                            textColor = Color.DKGRAY
                        }
                        axisLeft.apply {
                            setDrawGridLines(false)
                            setDrawAxisLine(false)
                            setDrawLabels(true)

                            spaceTop = 4f

                            textColor = Color.DKGRAY
                        }
                        xAxis.apply {
                            axisMaximum = 7f
                            setDrawLabels(true)
                            setDrawGridLines(false)
                            setDrawAxisLine(true)
                            position= XAxis.XAxisPosition.BOTTOM
                            textColor =  Color.DKGRAY
                        }

                        legend.apply {
                            textColor =  Color.DKGRAY
                        }
                        description.apply {
                            textColor =  Color.DKGRAY
                        }

                    }

                },
                update = {
                    val valueList = ArrayList<Double>()
                    val entries = ArrayList<BarEntry>()
                    val title = "Title"

                    //input data

                    //input data
                    for (i in 0..5) {
                        valueList.add(i * 100.1)
                    }

                    //fit the data into a bar

                    //fit the data into a bar
                    for (i in valueList.indices) {
                        val barEntry = BarEntry(i.toFloat(), valueList[i].toFloat())
                        entries.add(barEntry)
                    }

                    val barDataSet = BarDataSet(entries, title)

                    val data = BarData(barDataSet)
                    it.data = data
                    it.invalidate()
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
        }
    })
}

@Composable
fun TopAppBarDashboardReport() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    40.dp
                )
                .padding(
                    horizontal = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Laporan Keuanganmu",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = ""
            )
        }
        TabRow(
            selectedTabIndex = 0,
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier.height(
                44.dp
            )
        ) {
            Tab(selected = true, onClick = { /*TODO*/ }) {
                Text(
                    text = "Laporan",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium
                )
            }
            Tab(selected = false, onClick = { /*TODO*/ }) {
                Text(
                    text = "Saran",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenReport() {
    BaseMainApp(
        topAppBar = {
            TopAppBarDashboardReport()
        }
    ) {
        ScreenReport(it)
    }
}