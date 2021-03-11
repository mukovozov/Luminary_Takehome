package cvdevelopers.githubstalker

import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.models.Id
import cvdevelopers.takehome.models.Name
import cvdevelopers.takehome.models.Picture

object ClientCreator {
    fun createClient(
        email: String = "email",
        id: Id = createId(),
        name: Name = createName(),
        picture: Picture = createPicture()
    ) = Client(email, id, name, picture)

    private fun createId(
        name: String = "name",
        value: String? = null
    ) = Id(name, value)

    private fun createName(
        first: String = "first",
        last: String = "last",
        title: String = "title"
    ) = Name(first, last, title)

    private fun createPicture(
        large: String = "large",
        medium: String = "medium",
        thumbnail: String = "thumbnail"
    ) = Picture(large, medium, thumbnail)
}