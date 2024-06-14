package com.rushi.studyfy.session

import com.rushi.studyfy.domain.Session
import com.rushi.studyfy.domain.Subject

data class SessionState(
	val subjects: List<Subject> = emptyList(),
	val sessions: List<Session> = emptyList(),
	val relatedToSubject: String? = null,
	val subjectId: Int? = null,
	val session: Session? = null
)