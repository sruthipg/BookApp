package com.tapadoo.books.data.repository

import com.tapadoo.books.connection.BookService
import com.tapadoo.books.data.model.Books
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookListRepository @Inject constructor(private val bookService: BookService) {
    suspend fun getBooks(): Flow<List<Books>> =  flow{
        emit(bookService.getBooks())
    }

}