package com.tolanguage.serivce

import com.tolanguage.model.dto.RuleFormDto
import com.tolanguage.model.dto.RuleSlimDto
import com.tolanguage.model.entity.Rule
import com.tolanguage.model.entity.SubRule
import com.tolanguage.model.exception.EntityNotFoundException
import com.tolanguage.repository.RuleRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RuleService(
    val ruleRepository: RuleRepository,
    val courseService: CourseService,
    val subRulesService: SubRulesService
) {

    fun add(courseId: String, ruleFormDto: RuleFormDto): Unit =
        courseService.getById(courseId)
            .run {
                ruleRepository.insert(
                    Rule(
                        title = ruleFormDto.title,
                        course = this
                    )
                )
            }.run {
                subRulesService.saveAll(ruleFormDto.subRules.map {
                    SubRule(
                        text = it.text,
                        rule = this
                    )
                })
            }

    fun getPage(courseId: String, pageable: Pageable): Page<RuleSlimDto> =
        ruleRepository.findAllByCourseId(ObjectId(courseId), pageable).map {
            toSlimDto(it)
        }

    fun getById(id: String, courseId: String): Rule =
        ruleRepository.findByIdAndCourseId(ObjectId(id), ObjectId(courseId))
            ?: throw EntityNotFoundException("Rule not found by id [$id] and courseId [$courseId]")

    fun getDtoById(id: String, courseId: String): RuleSlimDto =
        toSlimDto(getById(id, courseId))

    fun calculateRules(courseId: String): Long = ruleRepository.findAllByCourseId(ObjectId(courseId)).size.toLong()

    private fun toSlimDto(entity: Rule): RuleSlimDto =
        RuleSlimDto(
            id = entity.id.toString(),
            title = entity.title,
            subRules = subRulesService.getAllDtoByRuleId(entity.id)
        )
}