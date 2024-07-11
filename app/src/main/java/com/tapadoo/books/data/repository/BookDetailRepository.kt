package com.tapadoo.books.data.repository

import com.tapadoo.books.connection.BookService
import com.tapadoo.books.data.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookDetailRepository @Inject constructor( private val bookService: BookService) {
    fun getBook(id: Int): Flow<Result<Book>> = flow {
        try {
            val book = bookService.getBook(id)
            emit(Result.success(book))
        } catch (e: IOException) {
            emit(Result.failure(e))
        } catch (e: HttpException) {
            emit(Result.failure(e))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}