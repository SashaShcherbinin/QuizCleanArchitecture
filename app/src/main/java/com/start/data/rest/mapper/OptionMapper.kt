package com.start.data.rest.mapper

import com.start.data.rest.dto.OptionDto
import com.start.data.rest.dto.StringsDto
import com.start.domain.entity.Option
import javax.inject.Inject

class OptionMapper
@Inject
constructor() {

    fun map(dto: OptionDto, stringsDto: StringsDto): Option = Option(
        text = stringsDto.en[dto.displayText]!!,
        value = dto.value
    )
}