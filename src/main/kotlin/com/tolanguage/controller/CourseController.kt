package com.tolanguage.controller

import com.tolanguage.model.dto.CourseFormDto
import com.tolanguage.model.dto.CourseSlimDto
import com.tolanguage.serivce.CourseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/courses")
class CourseController(
    val courseService: CourseService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun add(@RequestBody courseFormDto: CourseFormDto): Unit = courseService.add(courseFormDto)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getPage(pageable: Pageable): Page<CourseSlimDto> = courseService.getPage(pageable)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String): CourseSlimDto = courseService.getDtoById(id)

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    fun edit(@PathVariable("id") id: String, @RequestBody courseFormDto: CourseFormDto): CourseSlimDto =
        courseService.edit(id, courseFormDto)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): Unit = courseService.deleteById(id)
}