package com.tolanguage.controller

import com.tolanguage.model.dto.NoteFormDto
import com.tolanguage.serivce.NoteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/notes")
class NoteController(
    val noteService: NoteService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun add(noteFormDto: NoteFormDto) {

    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    fun getAll(): Page<NoteFormDto> {
//        return
//    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{id}")
//    fun getById(@PathVariable("id") id: Long): NoteFormDto {
//        return
//    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    fun edit(@PathVariable("id") id: Long, @RequestBody noteFormDto: NoteFormDto) {

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {

    }
}