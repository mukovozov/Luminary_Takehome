package cvdevelopers.takehome.database

import androidx.room.*
import cvdevelopers.takehome.models.Client
import io.reactivex.rxjava3.core.Single

@Dao
interface ClientDao {
    @Query("SELECT * FROM client")
    fun getClients(): Single<List<Client>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addClients(clients: List<Client>)

    @Query("DELETE FROM client")
    fun clearClients()

    @Transaction
    fun updateClients(clients: List<Client>) {
        clearClients()
        addClients(clients)
    }
}