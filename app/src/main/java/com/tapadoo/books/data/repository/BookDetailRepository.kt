package com.tapadoo.books.data.repository

import com.tapadoo.books.connection.BookService
import com.tapadoo.books.data.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookDetailRepository @Inject constructor( private val bookService: BookService) {
    fun getBook(id: Int): Flow<Book> = flow {
        emit(bookService.getBook(id))
    }
}