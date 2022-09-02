package com.tolanguage.controller

import com.tolanguage.model.dto.ContentSlimDto
import com.tolanguage.model.dto.ValueDto
import com.tolanguage.serivce.ContentService
import com.tolanguage.serivce.CourseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@SecurityRequirement(name = "Bearer")
@RequestMapping("/v1/contents")
class ContentController(
    val contentService: ContentService,
    val courseService: CourseService
) {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a course content", description = "This method get the course content by course id")
    @GetMapping("/courses/{id}")
    fun getCourseContent(@PathVariable("id") id: String): ContentSlimDto = contentService.getCourseContent(id)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get number of courses", description = "This method gets the number of courses")
    @GetMapping("/courses")
    fun getCourseNumber(): ValueDto = courseService.calculateCourses()
}