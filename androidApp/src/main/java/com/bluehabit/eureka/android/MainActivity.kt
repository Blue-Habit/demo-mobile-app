/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import app.trian.mvi.authenticationComponent
import app.trian.mvi.dashboardComponent
import app.trian.mvi.ui.internal.UIController
import app.trian.mvi.ui.internal.listener.EventListener
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.routes.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var eventListener: EventListener
    private lateinit var uiController: UIController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventListener = EventListener()
        setContent {
            uiController = rememberUIController(
                event = eventListener
            )

            BaseMainApp(controller = uiController) {
              NavHost(navController = uiController.navigator.navHost,
                  startDestination = Routes.Splash.routeName
              ){
                  authenticationComponent(uiController,eventListener)
                  dashboardComponent(uiController,eventListener)

              }
            }
        }
        listenFromChild()
    }

    private fun listenFromChild() {
        eventListener.addOnAppEventListener { event, params ->
            when (event) {
                "EXIT" -> finish()
            }
        }
    }
}
