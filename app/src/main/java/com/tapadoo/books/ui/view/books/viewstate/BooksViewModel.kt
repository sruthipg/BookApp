package com.tapadoo.books.ui.view.books.viewstate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapadoo.books.data.repository.BookListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val bookListRepository: BookListRepository) :
    ViewModel(){

    private val _bookListState = MutableStateFlow<BooksViewState>(BooksViewState.Loading)
    val bookListState: StateFlow<BooksViewState> get() = _bookListState

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            bookListRepository.getBooks().collect { result ->
                result.fold(
                    onSuccess = { books ->
                        _bookListState.value = BooksViewState.Success(books)
                    },
                    onFailure = { throwable ->
                        _bookListState.value = when (throwable) {
                            is IOException -> BooksViewState.Error.NetworkError
                            is HttpException -> BooksViewState.Error.ServerError
                            else -> BooksViewState.Error.CustomError(
                                throwable.localizedMessage ?: "Unexpected Error"
                            )
                        }
                    }
                )
            }
        }
    }
    }
