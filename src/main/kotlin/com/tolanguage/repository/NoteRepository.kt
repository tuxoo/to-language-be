package com.tolanguage.repository

import com.tolanguage.model.entity.Note
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository : MongoRepository<Note, String> {
}