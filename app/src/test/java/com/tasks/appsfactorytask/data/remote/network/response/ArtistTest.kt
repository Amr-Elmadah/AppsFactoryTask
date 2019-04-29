package com.tasks.appsfactorytask.data.remote.network.response

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ArtistTest {
    private lateinit var json: String
    private lateinit var artist: Artist

    @Before
    fun setup() {
        json =
            "{\"name\":\"Arctic Monkeys\",\"listeners\":\"3505316\"" +
                    ",\"mbid\":\"ada7a83c-e3e1-40f1-93f9-3e73dbc9298a\"" +
                    ",\"url\":\"https://www.last.fm/music/Arctic+Monkeys\"" +
                    ",\"streamable\":\"0\"" +
                    ",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/e04af7133fe2dd1bc7e43fadba6ace24.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/e04af7133fe2dd1bc7e43fadba6ace24.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/e04af7133fe2dd1bc7e43fadba6ace24.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/e04af7133fe2dd1bc7e43fadba6ace24.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/e04af7133fe2dd1bc7e43fadba6ace24.png\",\"size\":\"mega\"}]}"
        artist = Gson().fromJson<Artist>(json, Artist::class.java)
    }

    @Test
    fun checkNameNotEmpty() {
        Assert.assertTrue(!artist.name.isBlank())
    }

    @Test
    fun checkUrlNotEmpty() {
        Assert.assertTrue(!artist.url.isBlank())
    }

    @Test
    fun checkImagesNotEmpty() {
        Assert.assertTrue(!artist.image.isNullOrEmpty())
    }
}