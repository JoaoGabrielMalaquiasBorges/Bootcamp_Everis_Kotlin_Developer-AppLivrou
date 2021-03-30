package com.example.applivrou

import android.graphics.Bitmap

data class Book(
        var title: String,
        var author: String,/*,
        var edition: String,
        var releaseDate: String,
        var publisher: String,
        var description: String,*/
        var cover: Bitmap
)