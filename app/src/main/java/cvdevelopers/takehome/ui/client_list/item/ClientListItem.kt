package cvdevelopers.takehome.ui.client_list.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.ItemClientBinding
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.utils.image.ImageLoader

class ClientListItem(
    val client: Client,
    private val imageLoader: ImageLoader
) : BindableItem<ItemClientBinding>() {
    override fun getLayout() = R.layout.item_client

    override fun initializeViewBinding(view: View) = ItemClientBinding.bind(view)

    override fun bind(viewBinding: ItemClientBinding, position: Int) = with(viewBinding) {
        userName.text = client.name.full
        imageLoader.loadCircularImage(client.picture.thumbnail, userAvatar)
    }
}