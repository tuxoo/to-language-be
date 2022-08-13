package com.tolanguage.serivce

import com.tolanguage.model.dto.CourseFormDto
import com.tolanguage.model.dto.CourseSlimDto
import com.tolanguage.model.entity.Course
import com.tolanguage.model.exception.EntityNotFoundException
import com.tolanguage.repository.CourseRepository
import org.bson.types.ObjectId
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
                courseRepository.save(
                    Course(
                        language = dto.language,
                        description = dto.description,
                        user = this
                    )
                )
            }

    fun edit(id: String, courseFormDto: CourseFormDto): CourseSlimDto =
        toSlimDto(getById(id).run {
            courseRepository.save(
                Course(
                    id = ObjectId(id),
                    language = courseFormDto.language,
                    description = courseFormDto.description,
                    startedAt = courseFormDto.startedAt,
                    user = this.user
                )
            )
        })

    fun getPage(pageable: Pageable): Page<CourseSlimDto> =
        courseRepository.findAll(pageable).map {
            toSlimDto(it)
        }

    fun getById(id: String): Course = courseRepository.findOneById(ObjectId(id))
        ?: throw EntityNotFoundException("Course not found by id [$id]")

    fun getDtoById(id: String): CourseSlimDto = toSlimDto(getById(id))

    fun deleteById(id: String): Unit = courseRepository.deleteById(id)

    fun toSlimDto(entity: Course): CourseSlimDto =
        CourseSlimDto(
            id = entity.id.toString(),
            language = entity.language,
            description = entity.description,
            startedAt = entity.startedAt
        )
}