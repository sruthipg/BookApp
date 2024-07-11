package com.tapadoo.books.connection

import com.tapadoo.books.data.model.Book
import com.tapadoo.books.data.model.Books
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {

    @GET("${UrlConstants.BOOKS}")
    suspend fun getBooks():List<Books>

    @GET("${UrlConstants.BOOK}/{id}")
    suspend fun getBook(@Path("id") id: Int): Book

}