package com.artlogic.endoderapp.model

import com.artlogic.endoderapp.model.Encoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EncoderModule {

    @Singleton
    @Provides
    fun provideEncoder() = Encoder()
}