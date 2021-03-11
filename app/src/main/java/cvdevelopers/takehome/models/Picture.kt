package cvdevelopers.takehome.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class Picture(
    @PrimaryKey
    val large: String,
    val medium: String,
    val thumbnail: String
)