package com.tolanguage.repository

import com.tolanguage.model.entity.Course
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : MongoRepository<Course, String> {

    fun findOneById(id: ObjectId): Course?
}