package com.tapadoo.books.data.repository

import com.tapadoo.books.connection.BookService
import com.tapadoo.books.data.model.Books
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookListRepository @Inject constructor(private val bookService: BookService) {

    suspend fun getBooks(): Flow<Result<List<Books>>> = flow {
        try {
            val books = bookService.getBooks()
            emit(Result.success(books))
        } catch (e: IOException) {
            emit(Result.failure(e))
        } catch (e: HttpException) {
            emit(Result.failure(e))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}