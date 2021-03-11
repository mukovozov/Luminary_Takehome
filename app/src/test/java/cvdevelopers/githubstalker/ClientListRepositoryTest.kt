package cvdevelopers.githubstalker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import cvdevelopers.githubstalker.utils.RxRule
import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.database.ClientDao
import cvdevelopers.takehome.models.ApiResponse
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.repository.ClientListRepository
import cvdevelopers.takehome.repository.ClientListRepositoryImpl
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ClientListRepositoryTest {

    @get:Rule
    val rxRule = RxRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: ClientDao
    private lateinit var api: RandomUserApiEndpoint

    private lateinit var repository: ClientListRepository

    @Test
    fun `fetch users from server - request success`() {
        dao = mock {
            on { getClients() } doReturn (Single.just(emptyList()))
        }
        val clients = listOf(ClientCreator.createClient())
        val response = ApiResponse(clients)
        api = mock {
            on { getClients(any(), any()) } doReturn (Single.just(response))
        }
        repository = ClientListRepositoryImpl(api, dao)

        val result = repository.getUsers()
        val testObserver = TestObserver<List<Client>>()

        result.subscribe(testObserver)
        testObserver.assertComplete()
        val clientsResult = testObserver.values()
        assertThat(clientsResult.size).isEqualTo(clients.size)
    }
}