package com.tolanguage.controller

import com.tolanguage.model.dto.NoteFormDto
import com.tolanguage.model.dto.NoteSlimDto
import com.tolanguage.serivce.NoteService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses/{courseId}/notes")
class NoteController(
    val noteService: NoteService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun add(@PathVariable courseId: String, @RequestBody noteFormDto: NoteFormDto): Unit =
        noteService.add(courseId, noteFormDto)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getPage(@PathVariable("courseId") courseId: String, pageable: Pageable): Page<NoteSlimDto> =
        noteService.getPage(courseId, pageable)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String, @PathVariable("courseId") courseId: String): NoteSlimDto =
        noteService.getDtoById(id, courseId)

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    fun edit(
        @PathVariable("id") id: String,
        @PathVariable("courseId") courseId: String,
        @RequestBody noteFormDto: NoteFormDto
    ): NoteSlimDto = noteService.edit(id, courseId, noteFormDto)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String, @PathVariable("courseId") courseId: String) =
        noteService.deleteById(id, courseId)
}