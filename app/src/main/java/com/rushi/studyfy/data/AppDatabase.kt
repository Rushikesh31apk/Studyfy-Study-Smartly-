package com.rushi.studyfy.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rushi.studyfy.domain.Session
import com.rushi.studyfy.domain.Subject
import com.rushi.studyfy.domain.Task

@Database(
	entities = [Subject::class, Session::class, Task::class],
	version = 1
)
@TypeConverters(ColorListConverter::class)
abstract class AppDatabase: RoomDatabase() {

	abstract fun subjectDao(): SubjectDao
	abstract fun taskDao(): TaskDao
	abstract fun sessionDao(): SessionDao
}