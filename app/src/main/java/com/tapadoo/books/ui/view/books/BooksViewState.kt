package com.tapadoo.books.ui.view.books

import com.tapadoo.books.data.model.Books

sealed class BooksViewState {
    object Loading : BooksViewState()
    data class Success(val books: List<Books>) : BooksViewState()
    data class Error(val message: String) : BooksViewState()
}