package com.artlogic.endoderapp.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artlogic.endoderapp.view.presentation.EncodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EncodingViewModel @Inject constructor(
    private val encodeUseCase: EncodeUseCase,
): ViewModel() {

    private val _state: MutableState<EncodeState> = mutableStateOf(EncodeState())
    val state: State<EncodeState> = _state

    fun encodeString(input:String){
        viewModelScope.launch (Dispatchers.Default){
            val stringSplit = input.split(" ")
            val sb = StringBuilder()
            stringSplit.forEach {
                encodeUseCase(it).collectLatest { result ->
                    sb.append("${result}\n")
                }
            }
            withContext(Dispatchers.Main){
                _state.value = state.value.copy(output = sb.toString())
            }
        }
    }

    fun clear(){
        _state.value = state.value.copy(output = "")
    }
}