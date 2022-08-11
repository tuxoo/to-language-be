package com.tolanguage.serivce

import com.tolanguage.model.dto.NoteFormDto
import com.tolanguage.model.entity.Note
import com.tolanguage.repository.NoteRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class NoteService(
    val noteRepository: NoteRepository,
    val courseService: CourseService
) {

    fun add(courseId: String, dto: NoteFormDto): Unit =
        courseService.getById(courseId)
            .run {
                noteRepository.insert(
                    Note(
                        type = dto.type,
                        text = dto.text,
                        translation = dto.translation ?: "",
                        course = this
                    )
                )
            }

    fun getPage(pageable: Pageable): Page<NoteFormDto> =
        noteRepository.findAll(pageable).map {
            NoteFormDto(
                type = it.type,
                text = it.text,
                translation = it.translation,
                addedAt = it.addedAt
            )
        }
}