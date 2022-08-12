package com.tolanguage.model.enums

import com.tolanguage.model.dto.EnumDto

enum class NoteType(
    val description: String
) {
    EXP("Expression"),
    WORD("Word");

    companion object {
        fun toEnumDto() =
            values().map {
                EnumDto(
                    name = it.name,
                    description = it.description
                )
            }
    }
}