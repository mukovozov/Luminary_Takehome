package cvdevelopers.takehome.dagger.app

import android.app.Application
import android.content.Context
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: LuminaryTakeHomeApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideAppContext(): Context = app.applicationContext

}