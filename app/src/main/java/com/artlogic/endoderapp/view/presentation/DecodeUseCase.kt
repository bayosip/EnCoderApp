package com.artlogic.endoderapp.view.presentation

import com.artlogic.endoderapp.model.Encoder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DecodeUseCase @Inject constructor(
    private val decoder: Encoder,
) {

    suspend operator fun invoke(input:Int):Flow<String> = flow {
        emit(decoder.decodeEncryptedInt(input))
    }
}