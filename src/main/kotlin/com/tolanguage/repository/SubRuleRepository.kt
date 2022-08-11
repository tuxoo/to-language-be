package com.tolanguage.repository

import com.tolanguage.model.entity.SubRule
import org.springframework.data.mongodb.repository.MongoRepository

interface SubRuleRepository : MongoRepository<SubRule, String> {
}