package com.tolanguage.repository

import com.tolanguage.model.entity.Note
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : MongoRepository<Note, String> {

    fun findByIdAndCourseId(id: ObjectId, courseId: ObjectId): Note?

    fun findAllByCourseId(courseId: ObjectId, pageable: Pageable): Page<Note>

    fun findAllByCourseId(courseId: ObjectId): List<Note>

    fun deleteByIdAndCourseId(id: ObjectId, courseId: ObjectId)
}