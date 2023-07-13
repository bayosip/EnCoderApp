package com.artlogic.endoderapp.view.presentation

import com.artlogic.endoderapp.model.Encoder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EncodeUseCase @Inject constructor(
    private val encoder: Encoder,
) {
    suspend operator fun invoke(input:String):Flow<Long> = flow {
        emit(encoder.encodeStringToIntAnswer(input))
    }
}