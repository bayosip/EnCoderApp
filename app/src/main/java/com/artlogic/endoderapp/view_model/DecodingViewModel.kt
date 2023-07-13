package com.artlogic.endoderapp.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artlogic.endoderapp.view.presentation.DecodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DecodingViewModel @Inject constructor(
    private val decodeUseCase: DecodeUseCase,
) : ViewModel() {

    private val _state: MutableState<DecodeState> = mutableStateOf(DecodeState())
    val state: State<DecodeState> = _state


    fun decodeInputs(inputs: List<String>) {
        clearOutput()
        viewModelScope.launch(Dispatchers.Default) {
            val sb = StringBuilder()
            inputs.forEach {
                if (it.isNotBlank())
                    decodeUseCase.invoke(it.toInt()).collectLatest { output ->
                        sb.append("${output},")
                    }
            }
            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(decodedList = sb.toString())
            }
        }
    }

    fun clearOutput() {

        _state.value = state.value.copy(decodedList = "")
    }
}