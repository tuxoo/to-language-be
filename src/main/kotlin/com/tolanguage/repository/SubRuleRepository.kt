package com.tolanguage.repository

import com.tolanguage.model.entity.SubRule
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SubRuleRepository : MongoRepository<SubRule, String> {

    fun findAllByRuleId(id: ObjectId): List<SubRule>
}