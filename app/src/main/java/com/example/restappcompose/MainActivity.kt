package com.example.restappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.restappcompose.ui.HomeScreen.HomeViewModel
import com.example.restappcompose.ui.HomeScreen.StoryDetailsScreen
import com.example.restappcompose.ui.HomeScreen.TopStoriesScreen


class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            MyTheme() {
                // A surface container using the 'background' color from the theme
                NavHost(navController = navController, startDestination = "topStories") {
                    composable("topStories") {
                        TopStoriesScreen(navController, mainViewModel)
                    }
                    composable("storyDetails") {
                        StoryDetailsScreen(
                            mainViewModel.clickedStory,

                            )
                    }
                }
            }

        }
    }
}

