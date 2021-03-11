package cvdevelopers.takehome

import androidx.multidex.MultiDexApplication
import cvdevelopers.takehome.dagger.app.ApplicationComponent
import cvdevelopers.takehome.dagger.DaggerApplication
import timber.log.Timber

class LuminaryTakeHomeApplication : MultiDexApplication(), DaggerApplication {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initDi()
        Timber.plant(Timber.DebugTree())
    }

    override fun getApplicationComponent() = appComponent

    private fun initDi() {
        appComponent = ApplicationComponent.Initializer.init(this)
        appComponent.inject(this)
    }
}