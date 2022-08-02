@file:Suppress("OPT_IN_USAGE")

package com.start.presentation.feature.question

import androidx.lifecycle.viewModelScope
import com.start.R
import com.start.domain.entity.Answer
import com.start.domain.entity.Question
import com.start.domain.extention.combineWith
import com.start.domain.usecase.QuizUseCase
import com.start.presentation.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel
@Inject
constructor(private val quizUseCase: QuizUseCase) : BaseViewModel() {

    val questionId: MutableSharedFlow<String?> = MutableSharedFlow(1)

    val question: StateFlow<Question?> = questionId.flatMapLatest { questionId ->
        if (questionId == null) {
            quizUseCase.getFistQuestion().connectContentState()
        } else {
            quizUseCase.getQuestion(questionId).connectContentState()
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, initialValue = null)

    val answer: MutableStateFlow<Answer?> = MutableStateFlow(null)
    val enableNext: StateFlow<Boolean> = MutableStateFlow(false)
    val buttonText: StateFlow<String> = MutableStateFlow("")

    val goNextEvent = Channel<String>()
    val finishEvent = Channel<Unit>()

    init {
        viewModelScope.launch {
            answer.collect {
                (enableNext as MutableStateFlow).value = it != null
            }
        }
        viewModelScope.launch {
            theme.filterNotNull().combineWith(question.filterNotNull())
                .collect { (theme, question) ->
                    (buttonText as MutableStateFlow).value =
                        if (question.nextId != null) theme.resources.getString(R.string.question_button_next)
                        else theme.resources.getString(R.string.question_button_finish)
                }
        }
    }

    fun onClickNextButton() {
        viewModelScope.execute {
            quizUseCase.saveAnswer(answer.value!!)
            if (question.value!!.nextId == null) {
                finishEvent.trySend(Unit)
            } else {
                goNextEvent.trySend(question.value!!.nextId!!)
            }
        }
    }

}