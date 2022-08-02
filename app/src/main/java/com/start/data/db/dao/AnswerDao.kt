package com.start.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.start.data.db.table.AnswerTable

@Dao
interface AnswerDao {

    @Query("SELECT * FROM answer")
    suspend fun getAll(): List<AnswerTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answerTable: AnswerTable)
}
