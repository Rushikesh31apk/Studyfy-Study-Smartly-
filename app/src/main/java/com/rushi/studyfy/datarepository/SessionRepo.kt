package com.rushi.studyfy.datarepository

import com.rushi.studyfy.data.SessionDao
import com.rushi.studyfy.domain.Session
import com.rushi.studyfy.domainrepository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
	private val sessionDao: SessionDao
): SessionRepository {

	override suspend fun insertSession(session: Session) {
		sessionDao.insertSession(session)
	}

	override suspend fun deleteSession(session: Session) {
		sessionDao.deleteSession(session)
	}

	override fun getAllSessions(): Flow<List<Session>> {
		return sessionDao.getAllSessions()
			.map { sessions ->sessions.sortedByDescending { it.date }}
	}

	override fun getRecentFiveSessions(): Flow<List<Session>> {
		return sessionDao.getAllSessions()
			.map { sessions ->sessions.sortedByDescending { it.date }}
				.take(count = 5)
	}

	override fun getRecentTenSessionsForSubject(subjectId: Int): Flow<List<Session>> {
		return sessionDao.getRecentSessionsForSubject(subjectId)
			.map { sessions ->sessions.sortedByDescending { it.date }}
			.take(count = 10)
	}

	override fun getTotalSessionsDuration(): Flow<Long> {
		return sessionDao.getTotalSessionsDuration()
	}

	override fun getTotalSessionsDurationBySubject(subjectId: Int): Flow<Long> {
		return sessionDao.getTotalSessionsDurationBySubjectId(subjectId)
	}
}