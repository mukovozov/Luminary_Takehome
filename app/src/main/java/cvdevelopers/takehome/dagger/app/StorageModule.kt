package cvdevelopers.takehome.dagger.app

import android.content.Context
import androidx.room.Room
import cvdevelopers.takehome.database.AppDatabase
import cvdevelopers.takehome.database.ClientDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    companion object {
        private const val DATABASE_NAME = "app_database"
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideClientDao(appDatabase: AppDatabase): ClientDao = appDatabase.clientDao()
}