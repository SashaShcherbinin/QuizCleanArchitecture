package com.start.data.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

const val QUIZ_ID = "-1"

@Entity(tableName = "quiz")
data class QuizTable(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "start_id")
    val startId: String,
)

@Entity(
    tableName = "question",
    foreignKeys = [ForeignKey(
        entity = QuizTable::class,
        parentColumns = ["id"],
        childColumns = ["quiz_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class QuestionTable(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "quiz_id")
    val quizId: String,
    @ColumnInfo(name = "next_id")
    val nextId: String? = null,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "text")
    val text: String,
)

@Entity(
    tableName = "option", foreignKeys = [ForeignKey(
        entity = QuestionTable::class,
        parentColumns = ["id"],
        childColumns = ["question_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class OptionTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "question_id")
    val questionId: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "value")
    val value: String
)

