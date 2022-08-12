package com.tolanguage.serivce

import com.tolanguage.model.dto.SubRuleSlimDto
import com.tolanguage.model.entity.SubRule
import com.tolanguage.repository.SubRuleRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class SubRulesService(
    val subRuleRepository: SubRuleRepository
) {

    fun saveAll(subRules: List<SubRule>) =
        subRuleRepository.saveAll(subRules)

    fun getAllByRuleId(id: ObjectId): List<SubRule> =
        subRuleRepository.findAllByRuleId(id)

    fun getAllDtoByRuleId(id: ObjectId): List<SubRuleSlimDto> =
        getAllByRuleId(id).map {
            toSlimDto(it)
        }

    private fun toSlimDto(entity: SubRule): SubRuleSlimDto =
        SubRuleSlimDto(
            id = entity.id.toString(),
            text = entity.text,
            addedAt = entity.addedAt
        )
}