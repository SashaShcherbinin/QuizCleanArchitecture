package com.start.presentation.widget

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import com.start.databinding.QuestionNumberViewBinding
import com.start.databinding.QuestionSelectionItemViewBinding
import com.start.databinding.QuestionSelectionViewBinding
import com.start.databinding.QuestionTextViewBinding
import com.start.domain.entity.*


class QuestionView(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {

    private lateinit var _function: (Answer?) -> Unit

    private var _question: Question? = null

    fun setQuestion(question: Question) {
        _question = question
        removeAllViews()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        when (question.type) {
            QuestionType.TEXT -> initTextOption(inflater)
            QuestionType.CHECKBOX -> initSelectionOption(inflater)
            QuestionType.NUMBER -> initNumberOption(inflater)
        }

    }

    private fun initTextOption(inflater: LayoutInflater) {
        val binding = QuestionTextViewBinding.inflate(inflater, this, false)
        addView(binding.root)
        binding.valueTil.hint = _question!!.text
        binding.valueTie.addTextChangedListener {
            _function.invoke(it?.let { TextAnswer(_question!!.id, it.toString()) })
        }
    }

    private fun initNumberOption(inflater: LayoutInflater) {
        val binding = QuestionNumberViewBinding.inflate(inflater, this, false)
        addView(binding.root)
        binding.valueTil.hint = _question!!.text
        binding.valueTie.addTextChangedListener { editable: Editable? ->
            _function.invoke(
                editable?.toString()?.toFloatOrNull()?.let { NumberAnswer(_question!!.id, it) })
        }
    }

    private fun initSelectionOption(inflater: LayoutInflater) {
        val binding = QuestionSelectionViewBinding.inflate(inflater, this, false)
        addView(binding.root)
        _question!!.options!!.forEachIndexed { index, option ->
            val bindingItem = QuestionSelectionItemViewBinding.inflate(inflater, this, false)
            binding.selectionRg.addView(bindingItem.root)
            bindingItem.itemRb.text = option.text
            bindingItem.itemRb.id = index
            bindingItem.itemRb.setOnCheckedChangeListener { _, enable ->
                if (enable) {
                    _function.invoke(SelectionAnswer(_question!!.id, option))
                }
            }
        }
    }

    fun setAnswerListener(function: (Answer?) -> Unit) {
        _function = function
    }

}