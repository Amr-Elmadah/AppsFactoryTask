package com.tasks.appsfactorytask.data.remote.network.response

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ImageTest {
    private lateinit var json: String
    private lateinit var image: Image

    @Before
    fun setup() {
        json =
            "{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/e04af7133fe2dd1bc7e43fadba6ace24.png\",\"size\":\"small\"}"
        image = Gson().fromJson<Image>(json, Image::class.java)
    }

    @Test
    fun checkTextNotEmpty() {
        Assert.assertTrue(image.text.isNotEmpty())
    }

    @Test
    fun checkSizeNotEmpty() {
        Assert.assertTrue(image.size.isNotEmpty())
    }

    @Test
    fun checkSizeEqualsSmall() {
        Assert.assertTrue(image.size == "small")
    }
}