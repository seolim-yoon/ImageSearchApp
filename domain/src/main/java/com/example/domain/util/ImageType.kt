package com.example.domain.util

enum class ImageType {
    IMAGE, VIDEO;

    companion object {
        fun fromValue(value: String): ImageType {
            return ImageType.entries.find { it.name == value } ?: IMAGE
        }
    }
}