package com.example.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDTO(
    @SerialName("documents")
    val documents: List<VideoDocument> = listOf(),
    @SerialName("meta")
    val meta: Meta = Meta()
) {
    @Serializable
    data class VideoDocument(
        @SerialName("author")
        val author: String = "",
        @SerialName("datetime")
        val datetime: String = "",
        @SerialName("play_time")
        val playTime: Int = 0,
        @SerialName("thumbnail")
        val thumbnail: String = "",
        @SerialName("title")
        val title: String = "",
        @SerialName("url")
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