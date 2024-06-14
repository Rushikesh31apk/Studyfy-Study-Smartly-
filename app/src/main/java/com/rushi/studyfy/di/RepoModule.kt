package com.rushi.studyfy.di

import com.rushi.studyfy.datarepository.SessionRepositoryImpl
import com.rushi.studyfy.datarepository.SubjectRepositoryImpl
import com.rushi.studyfy.datarepository.TaskRepositoryImpl
import com.rushi.studyfy.domainrepository.SessionRepository
import com.rushi.studyfy.domainrepository.SubjectRepository
import com.rushi.studyfy.domainrepository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

	@Singleton
	@Binds
	abstract fun bindSubjectRepository(
		impl: SubjectRepositoryImpl
	): SubjectRepository

	@Singleton
	@Binds
	abstract fun bindTaskRepository(
		impl: TaskRepositoryImpl
	): TaskRepository

	@Singleton
	@Binds
	abstract fun bindSessionRepository(
		impl: SessionRepositoryImpl
	): SessionRepository
}