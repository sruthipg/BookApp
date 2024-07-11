package com.tapadoo.books.data.model

import java.io.Serializable
/*
 "id": 100,
    "title": "Code Complete: A Practical Handbook of Software Construction",
    "isbn": "978-0735619678",
    "description": "Widely considered one of the best practical guides to programming, Steve McConnell’s original CODE COMPLETE has been helping developers write better software for more than a decade. Now this classic book has been fully updated and revised with leading-edge practices—and hundreds of new code samples—illustrating the art and science of software construction. Capturing the body of knowledge available from research, academia, and everyday commercial practice, McConnell synthesizes the most effective techniques and must-know principles into clear, pragmatic guidance. No matter what your experience level, development environment, or project size, this book will inform and stimulate your thinking—and help you build the highest quality code.",
    "price": 2954,
    "currencyCode": "EUR",
    "author": "Mike Riley"
* */
data class Book(private val id: Int,
    private val title: String,
    private val isbn: String,
    private val description: String,
    private val currencyCode: String,
    private val author: String
    ): Serializable
