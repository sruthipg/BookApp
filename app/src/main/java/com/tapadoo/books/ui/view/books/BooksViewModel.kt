package com.tapadoo.books.ui.view.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapadoo.books.data.repository.BookListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val bookListRepository: BookListRepository) :
    ViewModel(){

    private val _bookListState = MutableStateFlow<BooksViewState>(BooksViewState.Loading)
    val bookListState: StateFlow<BooksViewState> = _bookListState



    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            bookListRepository.getBooks()
                .onStart { _bookListState.value = BooksViewState.Loading }
                .catch { e -> _bookListState.value = BooksViewState.Error(e.message ?: "Unknown Error") }
                .collect { books -> _bookListState.value = BooksViewState.Success(books) }
        }
    }
}