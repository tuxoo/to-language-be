package com.tolanguage.repository

import com.tolanguage.model.entity.Course
import org.springframework.data.mongodb.repository.MongoRepository


interface CourseRepository : MongoRepository<Course, String> {

    fun findOneById(id: String): Course?
}