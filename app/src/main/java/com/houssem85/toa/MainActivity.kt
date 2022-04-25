package com.houssem85.toa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.houssem85.toa.core.ui.theme.TOATheme
import com.houssem85.toa.tasklist.ui.TaskListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TOATheme {
                // LoginScreen()
                TaskListScreen()
            }
        }
    }
}
