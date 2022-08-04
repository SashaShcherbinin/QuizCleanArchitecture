package com.start.data.rest.mapper

import com.start.data.extention.getJsonByResource
import com.start.data.rest.dto.QuestionDto
import com.start.data.rest.dto.StringsDto
import com.start.domain.entity.Option
import com.start.domain.entity.Question
import com.start.domain.entity.QuestionType
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class QuestionMapperTest {

    @Test
    fun map() {
        /* Given */
        val expected = Question(
            id = "q_farmer_name",
            nextId = "q_farmer_gender",
            options = null,
            type = QuestionType.TEXT,
            text = "translation_3444"
        )
        val typeMapper = mock<QuestionTypeMapper> {
            on { map(any()) } doReturn QuestionType.TEXT
        }
        val optionMapper = mock<OptionMapper> {
            on { map(any(), any()) } doReturn Option("1", "2")
        }
        val en = mapOf("q_farmer_name" to "translation_3444")
        val stringsDto = StringsDto(en)
        /* When */
        val questionDto = getJsonByResource<QuestionDto>("dto/question.json")
        val actual = QuestionMapper(typeMapper, optionMapper).map(questionDto, stringsDto)
        /* Then */
        Assert.assertEquals(expected, actual)
    }
}