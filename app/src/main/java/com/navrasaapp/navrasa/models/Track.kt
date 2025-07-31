package com.navrasaapp.navrasa.models

data class Track(
    val title: String,
    val artist: String,
    val imageRes: Int,       // main thumbnail or icon
    val albumArtRes: Int,    // additional artwork, e.g., album cover
    val listeners: String
)