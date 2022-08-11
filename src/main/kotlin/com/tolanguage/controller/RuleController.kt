package com.tolanguage.controller

import com.tolanguage.model.dto.RuleFormDto
import com.tolanguage.serivce.RuleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses/{courseId}/rules")
class RuleController(
    val ruleService: RuleService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun add(@PathVariable courseId: String, @RequestBody ruleFormDto: RuleFormDto) {

    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    fun getAll(): Page<RuleFormDto> {
//        return
//    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{id}")
//    fun getById(@PathVariable("id") id: Long): RuleFormDto {
//        return
//    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    fun edit(@PathVariable("id") id: Long, @RequestBody ruleFormDto: RuleFormDto) {

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {

    }
}