package com.tolanguage.serivce

import com.tolanguage.repository.NoteRepository
import org.springframework.stereotype.Service

@Service
class NoteService(
    val noteRepository: NoteRepository
) {
}