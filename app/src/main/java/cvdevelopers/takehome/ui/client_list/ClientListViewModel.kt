package cvdevelopers.takehome.ui.client_list

import androidx.lifecycle.MutableLiveData
import cvdevelopers.takehome.converter.ClientListItemConverter
import cvdevelopers.takehome.repository.ClientListRepository
import cvdevelopers.takehome.ui.client_list.item.ClientListItem
import cvdevelopers.takehome.utils.lifecycle.update
import cvdevelopers.takehome.utils.rx.schedulersIoToMain
import cvdevelopers.takehome.utils.ui.*
import timber.log.Timber
import javax.inject.Inject

class ClientListViewModel @Inject constructor(
    private val repository: ClientListRepository,
    private val itemConverter: ClientListItemConverter,
) : BaseViewModel() {

    val viewState = MutableLiveData(ClientListViewState())

    init {
        loadUsers()
    }

    fun onPulledToRefresh() {
        loadUsers(shouldRefreshCache = true)
    }

    fun onRetryButtonClicked() {
        loadUsers()
    }

    private fun loadUsers(shouldRefreshCache: Boolean = false) {
        repository.getUsers(shouldRefreshCache)
            .toObservable()
            .map { itemConverter.convert(it) }
            .map<ScreenState<List<ClientListItem>>> { clients -> Content(clients) }
            .startWithItem(if (shouldRefreshCache) RefreshLoading() else Loading())
            .onErrorReturn { Stub(it) }
            .schedulersIoToMain()
            .subscribe(
                { screenState ->
                    viewState.update {
                        copy(screenState = screenState)
                    }
                },
                (Timber::e)
            )
            .disposeOnViewModelDestroy()
    }
}