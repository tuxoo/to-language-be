package com.tolanguage.repository

import com.tolanguage.model.entity.Rule
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface RuleRepository : MongoRepository<Rule, String> {

    fun findAllByCourseId(courseId: ObjectId, pageable: Pageable): Page<Rule>

    fun findByIdAndCourseId(id: ObjectId, courseId: ObjectId): Rule?
}