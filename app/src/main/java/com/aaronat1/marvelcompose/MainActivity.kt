package com.aaronat1.marvelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.aaronat1.marvelcompose.data.entities.Character
import com.aaronat1.marvelcompose.ui.navigation.Navigation
import com.aaronat1.marvelcompose.ui.screens.chracters.CharactersScreen
import com.aaronat1.marvelcompose.ui.theme.MarvelComposeTheme


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelApp {
                Navigation()
            }
        }
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
