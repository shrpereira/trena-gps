package br.com.intelize.trenagps

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.intelize.trenagps.di.CommonModules
import br.com.intelize.trenagps.di.RepositoryModule
import br.com.intelize.trenagps.di.ViewModelModule
import org.koin.android.ext.android.startKoin

@Suppress("unused")
class TrenaApplication : Application() {

	companion object {
		const val USER_PREFERENCES = "userPreferences"

		lateinit var instance: TrenaApplication

		fun getApplication(): TrenaApplication {
			return instance
		}
	}

	private val appModules = listOf(CommonModules, ViewModelModule, RepositoryModule)

	override fun onCreate() {
		super.onCreate()

		instance = this
		startKoin(this, appModules)
	}

	fun getSharedPreferences(name: String): SharedPreferences {
		return applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
	}
}