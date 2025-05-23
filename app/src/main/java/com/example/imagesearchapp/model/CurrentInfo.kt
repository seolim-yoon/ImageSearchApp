package com.example.imagesearchapp.model

import com.example.imagesearchapp.util.DEFAULT_KEYWORD

data class CurrentInfo(
    val keyword: String = DEFAULT_KEYWORD,
    val page: Int = 0
)