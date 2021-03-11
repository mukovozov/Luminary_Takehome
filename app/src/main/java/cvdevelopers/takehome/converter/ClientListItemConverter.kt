package cvdevelopers.takehome.converter

import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.ui.client_list.item.ClientListItem
import cvdevelopers.takehome.utils.image.ImageLoader
import javax.inject.Inject

class ClientListItemConverter @Inject constructor(
    private val imageLoader: ImageLoader
) {
    fun convert(clients: List<Client>): List<ClientListItem> {
        return clients.map { ClientListItem(it, imageLoader) }
    }
}