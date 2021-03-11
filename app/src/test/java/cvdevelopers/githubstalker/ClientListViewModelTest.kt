package cvdevelopers.githubstalker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import cvdevelopers.githubstalker.utils.RxRule
import cvdevelopers.takehome.converter.ClientListItemConverter
import cvdevelopers.takehome.repository.ClientListRepository
import cvdevelopers.takehome.ui.client_list.ClientListViewModel
import cvdevelopers.takehome.ui.client_list.ClientListViewState
import cvdevelopers.takehome.ui.client_list.item.ClientListItem
import cvdevelopers.takehome.utils.image.ImageLoader
import cvdevelopers.takehome.utils.ui.Content
import cvdevelopers.takehome.utils.ui.Stub
import io.reactivex.rxjava3.core.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class ClientListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val rxRule = RxRule()

    private lateinit var repository: ClientListRepository
    private lateinit var itemConverter: ClientListItemConverter
    private val imageLoader: ImageLoader = mock()
    private val testObserver: Observer<ClientListViewState> = mock()

    private lateinit var viewModel: ClientListViewModel

    @Test
    fun `when viewModel starts - data loaded - show content`() {
        val client = ClientCreator.createClient()
        val clients = listOf(client)
        repository = mock {
            on { getUsers() } doReturn (Single.just(clients))
        }

        val items = clients.map { ClientListItem(it, imageLoader) }
        itemConverter = mock {
            on { convert(clients) } doReturn (items)
        }

        viewModel = ClientListViewModel(repository, itemConverter)
        viewModel.viewState.observeForever(testObserver)

        val expectedContent = Content(items)
        assertThat(viewModel.viewState.value?.screenState).isEqualTo(expectedContent)
    }

    @Test
    fun `when viewModel starts - network error - show stub`() {
        val expectedError = IllegalStateException("network error")

        repository = mock {
            on { getUsers() } doReturn (Single.error(expectedError))
        }

        itemConverter = mock()

        viewModel = ClientListViewModel(repository, itemConverter)
        viewModel.viewState.observeForever(testObserver)

        val expectedStub = Stub<List<ClientListItem>>(expectedError)
        assertThat(viewModel.viewState.value?.screenState).isEqualTo(expectedStub)
    }

}