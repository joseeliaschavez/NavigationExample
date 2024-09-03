package com.rangerforce.navigationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rangerforce.navigationexample.ui.theme.NavigationExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigableApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavigableApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
    NavHost(navController = navController, startDestination = "first") {
        composable("first") {
            FirstScreen(
                handleNavigation = { navController.navigate("second") },
                sharedViewModel = sharedViewModel
            )
        }
        composable("second") {
            SecondScreen(
                handleForwardNavigation = { navController.navigate("third") },
                handleBackwardNavigation = { navController.popBackStack() },
                sharedViewModel = sharedViewModel
            )
        }
        composable("third") {
            ThirdScreen(handleNavigation = {
                navController.navigate("first")
            })
        }
    }
}