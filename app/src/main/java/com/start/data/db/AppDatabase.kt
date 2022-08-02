package com.start.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.start.data.db.dao.AnswerDao
import com.start.data.db.dao.QuizDao
import com.start.data.db.table.AnswerTable
import com.start.data.db.table.OptionTable
import com.start.data.db.table.QuestionTable
import com.start.data.db.table.QuizTable

@Database(
    entities = [AnswerTable::class, QuizTable::class, QuestionTable::class, OptionTable::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun answerDao(): AnswerDao
    abstract fun quizDao(): QuizDao
}