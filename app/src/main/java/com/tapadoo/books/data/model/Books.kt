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

data class Books(
    val id: Int,
    val title: String,
    val isbn: String,
    val currencyCode: String,
    val price: Double,
    val author: String
) : Serializable
