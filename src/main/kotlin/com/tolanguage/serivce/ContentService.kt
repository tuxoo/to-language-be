package com.tolanguage.serivce

import com.tolanguage.model.dto.ContentSlimDto
import org.springframework.stereotype.Service

@Service
class ContentService(
    val noteService: NoteService,
    val ruleService: RuleService
) {

    fun getCourseContent(courseId: String): ContentSlimDto =
        ContentSlimDto(
            notes = noteService.calculateNotes(courseId),
            rules = ruleService.calculateRules(courseId)
        )
}