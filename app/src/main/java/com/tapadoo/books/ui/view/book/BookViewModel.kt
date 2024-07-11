package com.tapadoo.books.ui.view.book

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapadoo.books.data.repository.BookDetailRepository
import com.tapadoo.books.ui.view.books.BooksViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val bookDetailRepository: BookDetailRepository):
    ViewModel(){

    private val _bookDetailState = MutableStateFlow<BookViewState>(BookViewState.Loading)
    val bookDetailState: StateFlow<BookViewState> = _bookDetailState


    fun fetchBookDetail(id: Int) {
        viewModelScope.launch {
            bookDetailRepository.getBook(id)
                .onStart { _bookDetailState.value = BookViewState.Loading }
                .catch { e -> _bookDetailState.value = BookViewState.Error(e.message ?: "Unknown Error") }
                .collect { book -> _bookDetailState.value = BookViewState.Success(book) }
        }
    }
}