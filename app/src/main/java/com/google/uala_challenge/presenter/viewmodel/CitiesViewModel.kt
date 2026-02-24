package com.google.uala_challenge.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.uala_challenge.domain.model.CitiesState
import com.google.uala_challenge.presenter.bloc.CitiesBaseBloc
import com.google.uala_challenge.presenter.bloc.CitiesEvent
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
class CitiesViewModel @Inject constructor(
    @Named("CitiesBlocs") @JvmSuppressWildcards private val blocs: List<CitiesBaseBloc>
) : ViewModel() {

    private val _state = MutableStateFlow(CitiesState())
    val state: StateFlow<CitiesState> = _state

    fun sendEvent(event: CitiesEvent) {
        viewModelScope.launch {
            val bloc = blocs.firstOrNull { it.canHandle(event) } ?: return@launch
            bloc.handle(event) { reducer -> updateState(reducer) }
        }
    }

    private suspend fun updateState(reducer: suspend (CitiesState) -> CitiesState) {
        withContext(Dispatchers.Main) {
            val next = reducer(_state.value)
            _state.value = next
        }
    }
}
