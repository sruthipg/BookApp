package com.tapadoo.books.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapadoo.books.R
import com.tapadoo.books.ui.view.book.BookDetailScreen
import com.tapadoo.books.ui.view.books.BookListScreen

@Composable
fun NavGraph(startDestination: String = "bookList") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("bookList") {
            BookListScreen(navController, R.drawable.close_book)
        }
        composable("bookDetail/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")?.toInt() ?: 0
            BookDetailScreen(navController, bookId)
        }
    }
}