package com.tolanguage.serivce

import com.tolanguage.model.dto.NoteFormDto
import com.tolanguage.model.dto.NoteSlimDto
import com.tolanguage.model.entity.Note
import com.tolanguage.model.exception.EntityNotFoundException
import com.tolanguage.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class NoteService(
    val noteRepository: NoteRepository,
    val courseService: CourseService
) {

    fun edit(id: String, courseId: String, dto: NoteFormDto): NoteSlimDto =
        toSlimDto(getById(id, courseId).run {
            noteRepository.save(
                Note(
                    id = ObjectId(id),
                    type = dto.type,
                    text = dto.text,
                    translation = dto.translation ?: "",
                    addedAt = dto.addedAt,
                    course = this.course
                )
            )
        })

    fun add(courseId: String, dto: NoteFormDto): Unit =
        courseService.getById(courseId)
            .run {
                noteRepository.save(
                    Note(
                        type = dto.type,
                        text = dto.text,
                        translation = dto.translation ?: "",
                        course = this
                    )
                )
            }

    fun getPage(courseId: String, pageable: Pageable): Page<NoteSlimDto> =
        noteRepository.findAllByCourseId(ObjectId(courseId), pageable).map {
            toSlimDto(it)
        }

    fun getById(id: String, courseId: String): Note =
        noteRepository.findByIdAndCourseId(ObjectId(id), ObjectId(courseId))
            ?: throw EntityNotFoundException("Note not found by id [$id] and courseId [$courseId]")

    fun getDtoById(id: String, courseId: String): NoteSlimDto = toSlimDto(getById(id, courseId))

    fun deleteById(id: String, courseId: String): Unit =
        noteRepository.deleteByIdAndCourseId(ObjectId(id), ObjectId(courseId))

    private fun toSlimDto(entity: Note): NoteSlimDto =
        NoteSlimDto(
            id = entity.id.toString(),
            type = entity.type,
            text = entity.text,
            translation = entity.translation,
            addedAt = entity.addedAt
        )
}