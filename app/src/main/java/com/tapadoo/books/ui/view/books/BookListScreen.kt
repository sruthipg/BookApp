package com.tapadoo.books.ui.view.books

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapadoo.books.data.model.Books

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun BookListScreen(navController: NavHostController,  bookIcon: Int) {
    val viewModel: BooksViewModel = hiltViewModel()
    val bookListState by viewModel.bookListState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Book List") }) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (bookListState) {
                is BooksViewState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Loading...", modifier = Modifier.padding(16.dp))
                    }
                }
                is BooksViewState.Success -> {
                    LazyColumn {
                        items((bookListState as BooksViewState.Success).books) { book ->
                            BookItem(book, bookIcon) {
                                navController.navigate("bookDetail/${book.id}")
                            }
                        }
                    }
                }
                is BooksViewState.Error -> {
                    val errorMessage = when (val error = bookListState as BooksViewState.Error) {
                        is BooksViewState.Error.NetworkError -> "Network error, please check your internet connection."
                        is BooksViewState.Error.ServerError -> "Server error, please try again later."
                        is BooksViewState.Error.CustomError -> error.message
                    }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookItem(book: Books, bookIcon: Int, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(bookIcon), // Replace with your vector drawable resource ID
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1
                )
                Text(
                    text = "by ${book.author}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
                Text(
                    text = "ISBN: ${book.isbn}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Currency:  ${book.price} ${book.currencyCode}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}