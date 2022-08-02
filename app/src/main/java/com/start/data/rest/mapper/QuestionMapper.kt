package com.start.data.rest.mapper

import com.start.data.rest.dto.QuestionDto
import com.start.data.rest.dto.StringsDto
import com.start.domain.entity.Question
import javax.inject.Inject

class QuestionMapper
@Inject
constructor(private val typeMapper: QuestionTypeMapper, private val optionMapper: OptionMapper) {

    fun map(dto: QuestionDto, stringsDto: StringsDto): Question = Question(
        id = dto.id,
        nextId = dto.nextId,
        type = typeMapper.map(dto.questionType),
        text = stringsDto.en[dto.questionText]!!,
        options = dto.options.map { optionMapper.map(it, stringsDto) }.takeIf { it.isNotEmpty() }
    )
}