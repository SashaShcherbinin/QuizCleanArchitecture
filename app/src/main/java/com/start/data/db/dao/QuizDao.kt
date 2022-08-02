package com.start.data.db.dao

import androidx.room.*
import com.start.data.db.table.OptionTable
import com.start.data.db.table.QuestionTable
import com.start.data.db.table.QuizTable

@Dao
interface QuizDao {

    @Query("SELECT * FROM quiz WHERE id = :id")
    suspend fun getQuiz(id: String): QuizTable?

    @Query("SELECT * FROM question WHERE quiz_id = :quizId")
    suspend fun getQuestions(quizId: String): List<QuestionTable>?

    @Query("SELECT * FROM option WHERE question_id in(:questionIds)")
    suspend fun getOptions(questionIds: Array<String>): List<OptionTable>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quizTable: QuizTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg questionTable: QuestionTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg optionTable: OptionTable)

    @Transaction
    @Query("DELETE FROM quiz WHERE id = :id")
    suspend fun delete(id: String)
}
