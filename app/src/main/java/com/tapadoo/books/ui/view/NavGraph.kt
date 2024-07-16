package com.tapadoo.books.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapadoo.books.R
import com.tapadoo.books.ui.view.book.BookDetailScreen
import com.tapadoo.books.ui.view.books.BookListScreen

enum class Screen {
    BOOKLIST,
    BOOKDETAIL,
}
sealed class NavigationItem(val route: String) {
    object BookList : NavigationItem(Screen.BOOKLIST.name)
    object BookDetail : NavigationItem(Screen.BOOKDETAIL.name)
}
@Composable
fun NavGraph(startDestination: String = NavigationItem.BookList.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.BookList.route) {
            BookListScreen(navController, R.drawable.close_book)
        }
        composable("${NavigationItem.BookDetail.route}/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")?.toInt() ?: 0
            BookDetailScreen(navController, bookId,R.drawable.book_detail)
        }
    }
}