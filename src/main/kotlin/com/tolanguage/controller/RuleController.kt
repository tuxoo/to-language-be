package com.tolanguage.controller

import com.tolanguage.model.dto.RuleFormDto
import com.tolanguage.model.dto.RuleSlimDto
import com.tolanguage.serivce.RuleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@SecurityRequirement(name = "Bearer")
@RequestMapping("/v1/courses/{courseId}/rules")
class RuleController(
    val ruleService: RuleService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add rule", description = "This method adds a rule")
    @PostMapping
    fun add(@PathVariable courseId: String, @RequestBody ruleFormDto: RuleFormDto): Unit =
        ruleService.add(courseId, ruleFormDto)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gets rules page", description = "This method gets the rules page")
    @GetMapping
    fun getPage(@PathVariable("courseId") courseId: String, pageable: Pageable): Page<RuleSlimDto> =
        ruleService.getPage(courseId, pageable)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get rule", description = "This method gets the rule by the id")
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String, @PathVariable("courseId") courseId: String): RuleSlimDto =
        ruleService.getDtoById(id, courseId)

//    @ResponseStatus(HttpStatus.OK)
//    @Operation(summary = "Edit rule", description = "This method edits the rule by the id")
//    @PatchMapping("/{id}")
//    fun edit(
//        @PathVariable("id") id: String,
//        @PathVariable("courseId") courseId: String,
//        @RequestBody ruleFormDto: RuleFormDto
//    ) {
//
//    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @Operation(summary = "Delete rule", description = "This method deletes the rule by the id")
//    @DeleteMapping("/{id}")
//    fun delete(@PathVariable("id") id: String, @PathVariable("courseId") courseId: String) {
//
//    }
}