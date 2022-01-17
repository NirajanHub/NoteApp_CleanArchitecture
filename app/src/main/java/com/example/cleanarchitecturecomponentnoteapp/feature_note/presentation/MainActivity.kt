package com.example.cleanarchitecturecomponentnoteapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecturecomponentnoteapp.feature_note.presentation.add_edit_note.componenets.AddEditNoteScreen
import com.example.cleanarchitecturecomponentnoteapp.feature_note.presentation.notes.NotesScreen
import com.example.cleanarchitecturecomponentnoteapp.feature_note.presentation.util.Screen
import com.example.cleanarchitecturecomponentnoteapp.ui.theme.CleanArchitectureComponentNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureComponentNoteAppTheme {
                 Surface(
                    color = MaterialTheme.colors.background
                ) {
                     val navController = rememberNavController()
                     NavHost(
                         navController = navController,
                     startDestination = Screen.NoteScreen.route
                         ){
                            composable(route = Screen.NoteScreen.route){
                                NotesScreen(navController = navController)
                            }
                         composable(
                             route = Screen.AddEditNoteScreen.route +
                                     "?noteId{noteId}&noteColor={noteColor}",
                             arguments = listOf(
                                 navArgument(
                                     name = "noteId"
                                 ){
                                     type = NavType.IntType
                                     defaultValue = -1
                                 } ,
                                 navArgument(
                                     name = "noteColor"
                                 ){
                                     type = NavType.IntType
                                     defaultValue = -1
                                 }
                             )
                         ){
                             val color = it.arguments?.getInt("noteColor") ?: -1
                             AddEditNoteScreen(navController = navController , noteColor = color)
                         }
                     }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CleanArchitectureComponentNoteAppTheme {
        Greeting("Android")
    }
}