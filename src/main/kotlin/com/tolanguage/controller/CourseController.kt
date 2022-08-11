package com.tolanguage.controller

import com.tolanguage.model.dto.CourseFormDto
import com.tolanguage.serivce.CourseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
class CourseController(
    val courseService: CourseService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun add(courseFormDto: CourseFormDto) {

    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    fun getAll(): Page<CourseFormDto> {
//        return
//    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{id}")
//    fun getById(@PathVariable("id") id: Long): CourseFormDto {
//        return
//    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    fun edit(@PathVariable("id") id: Long, @RequestBody courseFormDto: CourseFormDto) {

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {

    }
}