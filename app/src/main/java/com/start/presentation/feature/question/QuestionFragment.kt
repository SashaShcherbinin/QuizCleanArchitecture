package com.start.presentation.feature.question

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.start.R
import com.start.databinding.QuestionFragmentBinding
import com.start.presentation.common.base.BaseMVVMBindFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow

@AndroidEntryPoint
class QuestionFragment : BaseMVVMBindFragment<QuestionViewModel, QuestionFragmentBinding>(
    QuestionViewModel::class,
    R.layout.question_fragment
) {

    override val viewModel: QuestionViewModel by viewModels()

    private val args: QuestionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.vm = viewModel
        observeValues()
        initViews()
        viewModel.questionId.tryEmit(args.id)
        dataBinding.toolbar.setNavigationOnClickListener { backPress() }
    }

    private fun initViews() {
        dataBinding.questionV.setAnswerListener {
            viewModel.answer.value = it
        }
    }

    private fun observeValues() {
        lifecycleScope.launchWhenCreated {
            viewModel.question
                .filterNotNull()
                .collect {
                    dataBinding.questionV.setQuestion(it)
                }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.goNextEvent.receiveAsFlow().collect { questionId ->
                findNavController().navigate(QuestionFragmentDirections.actionToNextQuestion(questionId))
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.finishEvent.receiveAsFlow().collect {
                findNavController().popBackStack()
            }
        }
    }
}