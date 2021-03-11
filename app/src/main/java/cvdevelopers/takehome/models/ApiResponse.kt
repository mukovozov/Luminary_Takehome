package cvdevelopers.takehome.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(val results: List<Client>)