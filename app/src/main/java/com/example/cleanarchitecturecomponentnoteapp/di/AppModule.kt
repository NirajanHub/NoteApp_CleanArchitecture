package com.example.cleanarchitecturecomponentnoteapp.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecturecomponentnoteapp.feature_note.domain.repository.NoteRepository
import com.example.cleanarchitecturecomponentnoteapp.feature_note.data.data_source.NoteDatabase
import com.example.cleanarchitecturecomponentnoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.example.cleanarchitecturecomponentnoteapp.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun provideNoteDataBase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotes(repository = repository),
            deleteNote = DeleteNote(repository = repository),
            addNote = AddNote(repository = repository),
            getNote = GetNote(repository = repository)
        )
    }
}

