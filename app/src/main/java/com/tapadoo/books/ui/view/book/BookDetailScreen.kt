package com.tapadoo.books.ui.view.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tapadoo.books.data.model.Book
import com.tapadoo.books.ui.view.book.viewstate.BookViewModel
import com.tapadoo.books.ui.view.book.viewstate.BookViewState
import com.tapadoo.books.ui.view.common.BookAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(navController: NavHostController, bookId: Int, bookDetailIcon: Int) {
    val viewModel: BookViewModel = hiltViewModel()
    val bookDetailState by viewModel.bookDetailState.collectAsState()

    LaunchedEffect(bookId) {
        viewModel.fetchBookDetail(bookId)
    }

    Scaffold(
        topBar = { BookAppBar(title = "Book Detail", showBackButton = true, navController = navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (bookDetailState) {
                is BookViewState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Loading...", modifier = Modifier.padding(16.dp))
                    }
                }

                is BookViewState.Success -> {
                    val book = (bookDetailState as BookViewState.Success).book

                    BookDetailContent(book, bookDetailIcon)

                }

                is BookViewState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = when (bookDetailState) {
                                is BookViewState.Error.NetworkError -> "Network Error: Please check your internet connection."
                                is BookViewState.Error.ServerError -> "Server Error: Please try again later."
                                is BookViewState.Error.CustomError -> (bookDetailState as BookViewState.Error.CustomError).message
                                else -> "Unknown Error"
                            },
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookDetailContent(book: Book, bookDetailIcon: Int) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(8.dp))
        BookIcon(
            painter = painterResource(id = bookDetailIcon),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Title:",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = book.title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = Int.MAX_VALUE,
            overflow = TextOverflow.Visible
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Author:",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = book.author,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "ISBN:",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = book.isbn,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Price:",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "${book.price / 100.0} ${book.currencyCode}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Description:",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = book.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }

}

@Composable
fun BookIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
    }
}



