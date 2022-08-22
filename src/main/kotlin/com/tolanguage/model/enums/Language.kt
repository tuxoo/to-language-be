package com.tolanguage.model.enums

import com.tolanguage.model.dto.EnumDto

enum class Language(
    val fullName: String
) {
    RU("Russian"),
    EN("English"),
    FR("French"),
    ES("Spanish"),
    IT("Italian"),
    ZH("Chinese"),
    DE("German");

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