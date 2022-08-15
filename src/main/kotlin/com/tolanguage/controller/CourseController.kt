package com.tolanguage.controller

import com.tolanguage.model.dto.CourseFormDto
import com.tolanguage.model.dto.CourseSlimDto
import com.tolanguage.serivce.CourseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
class CourseController(
    val courseService: CourseService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody courseFormDto: CourseFormDto): Unit = courseService.add(courseFormDto)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getPage(): List<CourseSlimDto> = courseService.getCourses()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable("id") id: String): CourseSlimDto = courseService.getDtoById(id)

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun edit(@PathVariable("id") id: String, @RequestBody courseFormDto: CourseFormDto): CourseSlimDto =
        courseService.edit(id, courseFormDto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: String): Unit = courseService.deleteById(id)
}