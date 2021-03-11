package cvdevelopers.takehome.dagger.app

import android.app.Application
import android.content.Context
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.database.ClientDao
import cvdevelopers.takehome.ui.MainActivity
import cvdevelopers.takehome.utils.image.ImageLoader
import dagger.Component
import javax.inject.Singleton

/**
 * Created by CamiloVega on 10/7/17.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkClientModule::class, StorageModule::class])
interface ApplicationComponent {
    fun inject(app: LuminaryTakeHomeApplication)
    fun inject(target: MainActivity)

    fun api(): RandomUserApiEndpoint
    fun clientDao(): ClientDao
    fun imageLoader(): ImageLoader
    fun app(): Application
    fun context(): Context

    class Initializer private constructor() {
        companion object {
            fun init(app: LuminaryTakeHomeApplication): ApplicationComponent {
                return DaggerApplicationComponent.builder()
                    .networkClientModule(NetworkClientModule())
                    .storageModule(StorageModule())
                    .applicationModule(ApplicationModule(app))
                    .build()
            }
        }
    }
}