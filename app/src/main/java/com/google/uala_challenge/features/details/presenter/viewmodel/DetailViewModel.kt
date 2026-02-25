package com.google.uala_challenge.features.details.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.uala_challenge.features.details.domain.model.DetailState
import com.google.uala_challenge.features.details.presenter.bloc.DetailBaseBloc
import com.google.uala_challenge.features.details.presenter.bloc.DetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.jvm.JvmSuppressWildcards
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("DetailBlocs") @JvmSuppressWildcards private val blocs: List<DetailBaseBloc>
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    fun sendEvent(event: DetailEvent) {
        viewModelScope.launch {
            val bloc = blocs.firstOrNull { it.canHandle(event) } ?: return@launch
            bloc.handle(event) { reducer -> updateState(reducer) }
        }
    }

    private suspend fun updateState(reducer: suspend (DetailState) -> DetailState) {
        withContext(Dispatchers.Main) {
            _state.value = reducer(_state.value)
        }
    }
}
