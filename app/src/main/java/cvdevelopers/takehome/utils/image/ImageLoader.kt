package cvdevelopers.takehome.utils.image

import android.app.Application
import android.widget.ImageView
import com.squareup.picasso.Picasso
import cvdevelopers.githubstalker.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoader @Inject constructor(context: Application) {

    private val picasso = Picasso.Builder(context).build()

    fun loadCircularImage(url: String, imageView: ImageView) {
        picasso.load(url)
            .transform(CircleTransformation())
            .placeholder(R.drawable.ic_profile_placeholder)
            .into(imageView)
    }
}