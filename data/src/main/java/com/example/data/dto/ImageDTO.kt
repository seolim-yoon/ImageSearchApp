package com.example.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDTO(
    val documents: List<ImageDocument> = listOf(),
    val meta: Meta = Meta()
) {
    @Serializable
    data class ImageDocument(
        val collection: String = "",
        val datetime: String = "",
        @SerialName("display_sitename")
        val displaySiteName: String = "",
        @SerialName("doc_url")
        val docUrl: String = "",
        val height: Int = 0,
        @SerialName("image_url")
        val imageUrl: String = "",
        @SerialName("thumbnail_url")
        val thumbnailUrl: String = "",
        val width: Int = 0
    )

    @Serializable
    data class Meta(
        @SerialName("is_end")
        val isEnd: Boolean = false,
        @SerialName("pageable_count")
        val pageableCount: Int = 0,
        @SerialName("total_count")
        val totalCount: Int = 0
    )
}