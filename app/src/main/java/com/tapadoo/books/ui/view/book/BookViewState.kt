package com.tapadoo.books.ui.view.book

import com.tapadoo.books.data.model.Book


sealed class BookViewState {
    object Loading : BookViewState()
    data class Success(val book: Book) : BookViewState()
    data class Error(val message: String) : BookViewState()
}