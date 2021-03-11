package cvdevelopers.takehome.ui.client_list

import cvdevelopers.takehome.ui.client_list.item.ClientListItem
import cvdevelopers.takehome.utils.ui.Loading
import cvdevelopers.takehome.utils.ui.ScreenState

data class ClientListViewState(
    val screenState: ScreenState<List<ClientListItem>> = Loading()
)