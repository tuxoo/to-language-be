package com.tolanguage.controller

import com.tolanguage.model.dto.EnumDto
import com.tolanguage.model.enums.Language
import com.tolanguage.model.enums.NoteType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/items")
class EnumController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/languages")
    fun getLanguages(): List<EnumDto> = Language.toEnumDto()

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/note-types")
    fun getNoteTypes(): List<EnumDto> = NoteType.toEnumDto()
}