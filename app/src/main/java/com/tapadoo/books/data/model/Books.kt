package com.tapadoo.books.data.model

import java.io.Serializable

/*
"id": 100,
    "title": "Code Complete: A Practical Handbook of Software Construction",
    "isbn": "978-0735619678",
    "price": 2954,
    "currencyCode": "EUR",
    "author": "Mike Riley"
* */

data class Books(private val id:Int,
    private val title: String,
    private val isbn: String,
    private val currencyCode: String,
    private val author: String
    ): Serializable
