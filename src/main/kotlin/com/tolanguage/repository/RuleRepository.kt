package com.tolanguage.repository

import com.tolanguage.model.entity.Rule
import org.springframework.data.mongodb.repository.MongoRepository

interface RuleRepository : MongoRepository<Rule, String> {
}