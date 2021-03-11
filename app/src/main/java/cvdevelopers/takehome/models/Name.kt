package cvdevelopers.takehome.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class Name(
    @PrimaryKey
    val first: String,
    val last: String,
    val title: String
) {
    @Ignore
    val full = "$first $last"
}