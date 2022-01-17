package com.example.cleanarchitecturecomponentnoteapp.feature_note.data.repository

import com.example.cleanarchitecturecomponentnoteapp.feature_note.domain.model.Note
import com.example.cleanarchitecturecomponentnoteapp.feature_note.domain.repository.NoteRepository
import com.example.cleanarchitecturecomponentnoteapp.feature_note.data.data_source.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNotesById(id: Int): Note? {
       return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note = note)
    }

    override suspend fun deleteNote(note: Note) {
       dao.deleteNote(note)
    }
}