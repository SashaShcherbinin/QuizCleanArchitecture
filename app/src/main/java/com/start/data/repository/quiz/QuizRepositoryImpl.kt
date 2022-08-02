package com.start.data.repository.quiz

import com.start.data.common.cashe.CachePolicy
import com.start.data.common.storage.LocalStorage
import com.start.data.db.AppDatabase
import com.start.data.db.mapper.AnswerMapper
import com.start.domain.entity.Answer
import com.start.domain.entity.Quiz
import com.start.domain.repository.QuizRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepositoryImpl
@Inject
constructor(
    private val networkStorage: QuizNetworkStorage,
    private val appDatabase: AppDatabase,
    private val answerMapper: AnswerMapper,
    quizDbStorage: QuizDbStorage
) :
    QuizRepository {

    private val localStorage: LocalStorage<Unit, Quiz> = LocalStorage(
        maxElements = 1,
        cachePolicy = CachePolicy.create(10, TimeUnit.SECONDS),
        network = { networkStorage.getQuiz() },
        dataBase = quizDbStorage
    )

    override fun getQuiz(): Flow<Quiz> = localStorage.get(Unit)

    override suspend fun saveAnswer(value: Answer) {
        appDatabase.answerDao().insertAnswer(answerMapper.map(value))
    }

    override suspend fun sendAnswers() {
        appDatabase.answerDao().getAll().let {
            Timber.d("send answers + $it")
            delay(5_000)
        }
    }
}