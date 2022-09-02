package com.tolanguage.controller

import com.tolanguage.model.dto.NoteFormDto
import com.tolanguage.model.dto.NoteSlimDto
import com.tolanguage.serivce.NoteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@SecurityRequirement(name = "Bearer")
@RequestMapping("/v1/courses/{courseId}/notes")
class NoteController(
    val noteService: NoteService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add note", description = "This method adds a new note")
    @PostMapping
    fun add(@PathVariable courseId: String, @RequestBody noteFormDto: NoteFormDto): Unit =
        noteService.add(courseId, noteFormDto)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get notes page", description = "This method gets the notes page")
    @GetMapping
    fun getPage(@PathVariable("courseId") courseId: String, pageable: Pageable): Page<NoteSlimDto> =
        noteService.getPage(courseId, pageable)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get note", description = "This method gets the note by the id")
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String, @PathVariable("courseId") courseId: String): NoteSlimDto =
        noteService.getDtoById(id, courseId)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit note", description = "This method edits the note by id")
    @PatchMapping("/{id}")
    fun edit(
        @PathVariable("id") id: String,
        @PathVariable("courseId") courseId: String,
        @RequestBody noteFormDto: NoteFormDto
    ): NoteSlimDto = noteService.edit(id, courseId, noteFormDto)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete note", description = "This method deletes the note by the id")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String, @PathVariable("courseId") courseId: String): Unit =
        noteService.deleteById(id, courseId)
}