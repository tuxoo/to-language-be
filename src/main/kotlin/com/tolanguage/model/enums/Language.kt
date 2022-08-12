package com.tolanguage.model.enums

import com.tolanguage.model.dto.EnumDto

enum class Language(
    val fullName: String
) {
    EN("English"),
    RU("Russian");

    companion object {
        fun toEnumDto() =
            Language.values().map {
                EnumDto(
                    name = it.name,
                    description = it.fullName
                )
            }
    }
}