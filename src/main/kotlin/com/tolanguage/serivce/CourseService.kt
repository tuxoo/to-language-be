package com.tolanguage.serivce

import com.tolanguage.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(
    val courseRepository: CourseRepository
) {
}