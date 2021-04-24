package com.example.applivrou.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
        var title: String,
        var author: String,/*,
        var edition: String,
        var releaseDate: String,
        var publisher: String,
        var description: String,*/
        var cover: String
) : Parcelable