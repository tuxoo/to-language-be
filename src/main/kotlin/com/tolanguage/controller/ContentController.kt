package com.tolanguage.controller

import com.tolanguage.model.dto.ContentSlimDto
import com.tolanguage.model.dto.ValueDto
import com.tolanguage.serivce.ContentService
import com.tolanguage.serivce.CourseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/contents")
class ContentController(
    val contentService: ContentService,
    val courseService: CourseService
) {

    @GetMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getCourseContent(@PathVariable("id") id: String): ContentSlimDto = contentService.getCourseContent(id)

    @GetMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    fun getCourseNumber(): ValueDto = courseService.calculateCourses()
}