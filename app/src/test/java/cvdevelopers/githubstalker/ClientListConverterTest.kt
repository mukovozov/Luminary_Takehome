package cvdevelopers.githubstalker

import com.nhaarman.mockitokotlin2.mock
import cvdevelopers.takehome.converter.ClientListItemConverter
import cvdevelopers.takehome.ui.client_list.item.ClientListItem
import cvdevelopers.takehome.utils.image.ImageLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ClientListConverterTest {

    private val imageLoader: ImageLoader = mock()

    private lateinit var converter: ClientListItemConverter

    @Test
    fun `verify that converter return items`() {
        converter = ClientListItemConverter(imageLoader)
        val clients = listOf(ClientCreator.createClient())

        val expectedItems = clients.map { ClientListItem(it, imageLoader) }
        val actualItems = converter.convert(clients)

        assertThat(expectedItems.size).isEqualTo(actualItems.size)
        val expectedClient = expectedItems[0]
        val actualClient = actualItems[0]
        assertThat(expectedClient.client).isEqualTo(actualClient.client)
    }
}