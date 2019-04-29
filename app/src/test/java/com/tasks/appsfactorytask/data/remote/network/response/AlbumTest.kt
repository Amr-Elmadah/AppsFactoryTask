package com.tasks.appsfactorytask.data.remote.network.response

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumTest {
    private lateinit var json: String
    private lateinit var album: Album

    @Before
    fun setup() {
        json =
            "{\"name\":\"Whatever People Say I Am, That's What I'm Not\",\"playcount\":67872310,\"url\":\"https://www.last.fm/music/Arctic+Monkeys/Whatever+People+Say+I+Am,+That%27s+What+I%27m+Not\",\"artist\":{\"name\":\"Arctic Monkeys\",\"mbid\":\"ada7a83c-e3e1-40f1-93f9-3e73dbc9298a\",\"url\":\"https://www.last.fm/music/Arctic+Monkeys\"},\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/3ef184531e0445eb828c81ee969d6e13.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/3ef184531e0445eb828c81ee969d6e13.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/3ef184531e0445eb828c81ee969d6e13.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/3ef184531e0445eb828c81ee969d6e13.png\",\"size\":\"extralarge\"}]}"
        album = Gson().fromJson<Album>(json, Album::class.java)
    }

    @Test
    fun checkNameNotEmpty() {
        Assert.assertTrue(!album.name.isBlank())
    }

    @Test
    fun checkUrlNotEmpty() {
        Assert.assertTrue(!album.url.isBlank())
    }

    @Test
    fun checkImagesNotEmpty() {
        Assert.assertTrue(!album.image.isNullOrEmpty())
    }

    @Test
    fun checkPlayCountNotEmpty() {
        Assert.assertTrue(album.playcount > 0)
    }
}