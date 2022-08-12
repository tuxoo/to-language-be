package com.tolanguage.controller

import com.tolanguage.model.dto.RuleFormDto
import com.tolanguage.model.dto.RuleSlimDto
import com.tolanguage.serivce.RuleService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses/{courseId}/rules")
class RuleController(
    val ruleService: RuleService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun add(@PathVariable courseId: String, @RequestBody ruleFormDto: RuleFormDto): Unit =
        ruleService.add(courseId, ruleFormDto)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getPage(@PathVariable("courseId") courseId: String, pageable: Pageable): Page<RuleSlimDto> =
        ruleService.getPage(courseId, pageable)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String, @PathVariable("courseId") courseId: String): RuleSlimDto =
        ruleService.getDtoById(id, courseId)

//    @ResponseStatus(HttpStatus.OK)
//    @PatchMapping("/{id}")
//    fun edit(
//        @PathVariable("id") id: String,
//        @PathVariable("courseId") courseId: String,
//        @RequestBody ruleFormDto: RuleFormDto
//    ) {
//
//    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    fun delete(@PathVariable("id") id: String, @PathVariable("courseId") courseId: String) {
//
//    }
}