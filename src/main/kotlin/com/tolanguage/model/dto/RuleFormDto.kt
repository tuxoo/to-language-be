package com.tolanguage.model.dto

data class RuleFormDto(
    val title: String,
    val subRules: List<SubRuleFormDto> = emptyList()
)
