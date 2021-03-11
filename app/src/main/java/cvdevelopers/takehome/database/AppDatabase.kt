package cvdevelopers.takehome.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.models.Id
import cvdevelopers.takehome.models.Name
import cvdevelopers.takehome.models.Picture

@Database(entities = [Client::class, Id::class, Name::class, Picture::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
}