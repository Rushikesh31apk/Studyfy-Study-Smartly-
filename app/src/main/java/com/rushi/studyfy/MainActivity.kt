package com.rushi.studyfy

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.rushi.studyfy.destinations.SessionScreenRouteDestination
import com.rushi.studyfy.session.StudySessionTimerService
import com.rushi.studyfy.ui.theme.StudyfyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	private var isBound by mutableStateOf(false)
	private lateinit var timerService: StudySessionTimerService
	private val connection = object : ServiceConnection {
		override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
			val binder = service as StudySessionTimerService.StudySessionTimerBinder
			timerService = binder.getService()
			isBound = true
		}

		override fun onServiceDisconnected(p0: ComponentName?) {
			isBound = false
		}
	}

	override fun onStart() {
		super.onStart()
		Intent(this, StudySessionTimerService::class.java).also { intent ->
			bindService(intent, connection, Context.BIND_AUTO_CREATE)
		}
	}
	@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			if (isBound) {
				StudyfyTheme {
					DestinationsNavHost(
						navGraph = NavGraphs.root,
						dependenciesContainerBuilder = {
							dependency(SessionScreenRouteDestination) { timerService }
						}
					)
				}
			}
		}
		requestPermission()
	}
	private fun requestPermission() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			ActivityCompat.requestPermissions(
				this,
				arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
				0
			)
		}
	}
	override fun onStop() {
		super.onStop()
		unbindService(connection)
		isBound = false
	}

}
