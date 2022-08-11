package com.tolanguage.controller

import com.tolanguage.model.dto.CourseFormDto
import com.tolanguage.serivce.CourseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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
    fun getAll(pageable: Pageable): Page<CourseFormDto> = courseService.getPage(pageable)

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