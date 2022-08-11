package com.tolanguage.serivce

import com.tolanguage.repository.RuleRepository
import org.springframework.stereotype.Service

@Service
class RuleService(
    val ruleRepository: RuleRepository
) {
}