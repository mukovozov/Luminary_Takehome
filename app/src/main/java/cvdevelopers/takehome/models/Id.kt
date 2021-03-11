package cvdevelopers.takehome.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class Id(
    @PrimaryKey
    val name: String,
    val value: String?
)