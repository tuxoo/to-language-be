package com.tolanguage.serivce

import com.tolanguage.model.dto.CourseFormDto
import com.tolanguage.model.entity.Course
import com.tolanguage.repository.CourseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CourseService(
    val courseRepository: CourseRepository,
    val userService: UserService
) {

    fun add(dto: CourseFormDto): Unit =
        userService.getCurrent()
            .run {
                courseRepository.insert(
                    Course(
                        language = dto.language,
                        description = dto.description,
                        user = this
                    )
                )
            }

    fun getPage(pageable: Pageable): Page<CourseFormDto> =
        courseRepository.findAll(pageable).map {
            CourseFormDto(
                language = it.language,
                description = it.description,
                startedAt = it.startedAt
            )
        }

    fun getById(id: String): Course = courseRepository.findOneById(id) ?: error("")

}