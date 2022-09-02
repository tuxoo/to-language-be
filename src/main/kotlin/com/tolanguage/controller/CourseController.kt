package com.tolanguage.controller

import com.tolanguage.model.dto.CourseFormDto
import com.tolanguage.model.dto.CourseSlimDto
import com.tolanguage.serivce.CourseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@SecurityRequirement(name = "Bearer")
@RequestMapping("/v1/courses")
class CourseController(
    val courseService: CourseService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add course", description = "This method adds a new course")
    @PostMapping
    fun add(@RequestBody courseFormDto: CourseFormDto): Unit = courseService.add(courseFormDto)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get courses page", description = "This method gets the courses page")
    @GetMapping
    fun getPage(): List<CourseSlimDto> = courseService.getCourses()

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get course", description = "This method gets the course by the id")
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String): CourseSlimDto = courseService.getDtoById(id)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit course", description = "This method edits the course by the id")
    @PatchMapping("/{id}")
    fun edit(@PathVariable("id") id: String, @RequestBody courseFormDto: CourseFormDto): CourseSlimDto =
        courseService.edit(id, courseFormDto)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete course", description = "This method deletes the course by the id")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): Unit = courseService.deleteById(id)
}