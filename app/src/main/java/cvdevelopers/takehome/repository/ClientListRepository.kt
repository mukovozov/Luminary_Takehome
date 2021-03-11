package cvdevelopers.takehome.repository

import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.database.ClientDao
import cvdevelopers.takehome.models.Client
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface ClientListRepository {
    fun getUsers(shouldRefreshCache: Boolean = false): Single<List<Client>>
}

class ClientListRepositoryImpl @Inject constructor(
    private val api: RandomUserApiEndpoint,
    private val dao: ClientDao
) : ClientListRepository {

    override fun getUsers(shouldRefreshCache: Boolean): Single<List<Client>> {
        return if (shouldRefreshCache) {
            loadClients()
                .doOnSuccess(dao::updateClients)
        } else {
            Single.concat(dao.getClients(), loadClients())
                .firstElement()
                .toSingle()
        }
    }

    // I thought that pagination will be fine here, but I didn't see requirement in ReadMe.
    private fun loadClients(): Single<List<Client>> {
        return api.getClients("1")
            .map { it.results }
            .doOnSuccess(dao::addClients)
    }
}