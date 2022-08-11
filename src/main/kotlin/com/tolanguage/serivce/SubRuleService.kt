package com.tolanguage.serivce

import com.tolanguage.repository.SubRuleRepository
import org.springframework.stereotype.Service

@Service
class SubRuleService(
    val subRuleRepository: SubRuleRepository
) {
}