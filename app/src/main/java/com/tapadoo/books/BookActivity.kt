package com.tapadoo.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.tapadoo.books.ui.view.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                NavGraph()
            }
        }
    }
}

