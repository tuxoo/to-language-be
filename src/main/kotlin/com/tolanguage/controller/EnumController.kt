package com.tolanguage.controller

import com.tolanguage.model.dto.EnumDto
import com.tolanguage.model.enums.Language
import com.tolanguage.model.enums.NoteType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@SecurityRequirement(name = "Bearer")
@RequestMapping("/v1/items")
class EnumController {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get languages", description = "This method gets language list")
    @GetMapping("/languages")
    fun getLanguages(): List<EnumDto> = Language.toEnumDto()

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get note's types", description = "This method get note's type list")
    @GetMapping("/note-types")
    fun getNoteTypes(): List<EnumDto> = NoteType.toEnumDto()
}