package com.tapadoo.books.ui.view.book.viewstate

import com.tapadoo.books.data.model.Book


sealed class BookViewState {
    object Loading : BookViewState()
    data class Success(val book: Book) : BookViewState()
    sealed class Error : BookViewState() {
        object NetworkError : Error()
        object ServerError : Error()
        data class CustomError(val message: String) : Error()
    }
}