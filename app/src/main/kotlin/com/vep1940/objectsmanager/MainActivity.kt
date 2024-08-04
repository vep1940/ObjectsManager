package com.vep1940.objectsmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vep1940.presentation.screen.detail.detailScreen
import com.vep1940.presentation.screen.detail.navigateToDetailScreen
import com.vep1940.presentation.screen.list.LIST_SCREEN_ROUTE
import com.vep1940.presentation.screen.list.listScreen
import com.vep1940.presentation.screen.objectform.navigateToObjectForm
import com.vep1940.presentation.screen.objectform.objectForm
import com.vep1940.presentation.screen.relationform.navigateToRelationForm
import com.vep1940.presentation.screen.relationform.relationForm
import com.vep1940.presentation.theme.ObjectsManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {

            ObjectsManagerTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = LIST_SCREEN_ROUTE,
                ) {
                    listScreen(
                        navigateToDetailScreen = { id -> navController.navigateToDetailScreen(id) },
                        openObjectForm = { navController.navigateToObjectForm(null) },
                        okError = { finish() },
                    )
                    detailScreen(
                        openObjectForm = { id -> navController.navigateToObjectForm(id) },
                        openRelationForm = { id, relatedId ->
                            navController.navigateToRelationForm(
                                id,
                                relatedId
                            )
                        },
                        okError = { navController.popBackStack() },
                    )
                    objectForm(
                        saveObject = { navController.popBackStack() },
                    )
                    relationForm(
                        saveObject = { navController.popBackStack() },
                    )
                }
            }
        }
    }
}