package com.tapadoo.books.ui.view.books

import com.tapadoo.books.data.model.Books

sealed class BooksViewState {
    object Loading : BooksViewState()
    data class Success(val books: List<Books>) : BooksViewState()
    sealed class Error : BooksViewState() {
        object NetworkError : Error()
        object ServerError : Error()
        data class CustomError(val message: String) : Error()
    }
}