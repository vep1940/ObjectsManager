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
                        navigateToDetailScreen = { navController.navigateToDetailScreen() }
                    )
                    detailScreen()
                }
            }
        }
    }
}