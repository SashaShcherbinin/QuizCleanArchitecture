package com.start.presentation.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.start.domain.usecase.QuizUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class AnswerWorker
@AssistedInject
constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val questionUseCase: QuizUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            questionUseCase.sendAnswers()
            Result.success()
        } catch (e: Throwable) {
            Result.retry()
        }
    }

}