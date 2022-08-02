package com.start.data.repository.quiz

import androidx.room.Transaction
import com.start.data.common.storage.LocalStorage
import com.start.data.db.AppDatabase
import com.start.data.db.mapper.QuizDbMapper
import com.start.domain.entity.Quiz
import javax.inject.Inject

class QuizDbStorage
@Inject
constructor(private val appDatabase: AppDatabase, private val quizDbMapper: QuizDbMapper) :
    LocalStorage.DataBase<Unit, Quiz> {

    override suspend fun read(key: Unit): Quiz? {
        val quizDao = appDatabase.quizDao()
        val quiz = quizDao.getQuiz("-1")
        val questions = quizDao.getQuestions("-1")
        val options = questions?.let { entity ->
            quizDao.getOptions(entity.map { it.id }.toTypedArray())
        }
        return quizDbMapper.map(quiz, questions, options)
    }

    override suspend fun remove(key: Unit) {
        appDatabase.quizDao().delete("-1")
    }

    @Transaction
    override suspend fun insertOrUpdate(key: Unit, entity: Quiz) {
        val quizDao = appDatabase.quizDao()
        quizDao.delete("-1")
        quizDao.insert(quizDbMapper.mapToQuiz(entity))
        quizDao.insert(*quizDbMapper.mapToQuestions(entity).toTypedArray())
        quizDao.insert(*quizDbMapper.mapToOptions(entity).toTypedArray())
    }
}