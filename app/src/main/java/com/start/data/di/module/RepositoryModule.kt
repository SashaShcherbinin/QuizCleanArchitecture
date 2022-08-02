package com.start.data.di.module

import com.start.data.repository.quiz.QuizRepositoryImpl
import com.start.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun getUserRepository(userRepositoryImpl: QuizRepositoryImpl): QuizRepository =
        userRepositoryImpl

}