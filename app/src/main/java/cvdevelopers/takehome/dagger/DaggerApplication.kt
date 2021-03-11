package cvdevelopers.takehome.dagger

import cvdevelopers.takehome.dagger.app.ApplicationComponent

interface DaggerApplication {
    fun getApplicationComponent(): ApplicationComponent
}