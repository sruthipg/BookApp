package com.tapadoo.books.ui.view.book

import androidx.lifecycle.SavedStateHandle
import com.tapadoo.books.data.repository.BookDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(savedStateHandle: SavedStateHandle
                                        ,private val bookDetailRepository: BookDetailRepository){
    private val bookId: String = savedStateHandle["id"] ?:
    throw IllegalArgumentException("Missing book ID")


}