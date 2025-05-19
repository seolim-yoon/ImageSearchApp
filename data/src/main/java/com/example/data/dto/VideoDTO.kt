package com.example.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDTO(
    val documents: List<VideoDocument> = listOf(),
    val meta: Meta = Meta()
) {
    @Serializable
    data class VideoDocument(
        val author: String = "",
        val datetime: String = "",
        @SerialName("play_time")
        val playTime: Int = 0,
        val thumbnail: String = "",
        val title: String = "",
        val url: String = ""
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