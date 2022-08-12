package com.tolanguage.model.dto

data class RuleSlimDto(
    val id: String,
    val title: String,
    val subRules: List<SubRuleSlimDto> = emptyList()
)
