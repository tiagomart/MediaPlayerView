package com.tmrtapps.mediaplayerview

data class Audio(var data: String, var albumArt: Any?, var title: String, var artist: String) {
    constructor(data: String) : this(data, null, "", "")
}
