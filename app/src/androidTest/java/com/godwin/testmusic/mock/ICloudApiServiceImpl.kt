package com.godwin.testmusic.mock

import com.godwin.testmusic.network.ICloudApiService
import com.godwin.testmusic.network.model.ITuneResponse
import com.google.gson.Gson

class ICloudApiServiceImpl : ICloudApiService {
    val json =
        "{\"feed\":{\"author\":{\"name\":{\"label\":\"iTunes Store\"}, \"uri\":{\"label\":\"http://www.apple.com/itunes/\"}}, \"entry\":[\n" +
                "{\"im:name\":{\"label\":\"Bell Bottom Country\"}, \"im:image\":[\n" +
                "{\"label\":\"https://is4-ssl.mzstatic.com/image/thumb/Music112/v4/2c/d4/d1/2cd4d14d-d89c-d7d5-8ae7-bbca55e23c4f/4050538875423.jpg/55x55bb.png\", \"attributes\":{\"height\":\"55\"}}, \n" +
                "{\"label\":\"https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/2c/d4/d1/2cd4d14d-d89c-d7d5-8ae7-bbca55e23c4f/4050538875423.jpg/60x60bb.png\", \"attributes\":{\"height\":\"60\"}}, \n" +
                "{\"label\":\"https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/2c/d4/d1/2cd4d14d-d89c-d7d5-8ae7-bbca55e23c4f/4050538875423.jpg/170x170bb.png\", \"attributes\":{\"height\":\"170\"}}], \"im:itemCount\":{\"label\":\"16\"}, \"im:price\":{\"label\":\"\$10.99\", \"attributes\":{\"amount\":\"10.99\", \"currency\":\"USD\"}}, \"im:contentType\":{\"im:contentType\":{\"attributes\":{\"term\":\"Album\", \"label\":\"Album\"}}, \"attributes\":{\"term\":\"Music\", \"label\":\"Music\"}}, \"rights\":{\"label\":\"℗ 2022 This Is Hit, Inc. d/b/a Broken Bow Records\"}, \"title\":{\"label\":\"Bell Bottom Country - Lainey Wilson\"}, \"link\":{\"attributes\":{\"rel\":\"alternate\", \"type\":\"text/html\", \"href\":\"https://music.apple.com/us/album/bell-bottom-country/1654513456?uo=2\"}}, \"id\":{\"label\":\"https://music.apple.com/us/album/bell-bottom-country/1654513456?uo=2\", \"attributes\":{\"im:id\":\"1654513456\"}}, \"im:artist\":{\"label\":\"Lainey Wilson\", \"attributes\":{\"href\":\"https://music.apple.com/us/artist/lainey-wilson/907166363?uo=2\"}}, \"category\":{\"attributes\":{\"im:id\":\"6\", \"term\":\"Country\", \"scheme\":\"https://music.apple.com/us/genre/music-country/id6?uo=2\", \"label\":\"Country\"}}, \"im:releaseDate\":{\"label\":\"2022-11-28T00:00:00-07:00\", \"attributes\":{\"label\":\"November 28, 2022\"}}}, \n" +
                "{\"im:name\":{\"label\":\"Leave The Light On - EP\"}, \"im:image\":[\n" +
                "{\"label\":\"https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/30/17/1b/30171b77-7d87-bf60-49f1-fa0d6376f193/093624864899.jpg/55x55bb.png\", \"attributes\":{\"height\":\"55\"}}, \n" +
                "{\"label\":\"https://is3-ssl.mzstatic.com/image/thumb/Music122/v4/30/17/1b/30171b77-7d87-bf60-49f1-fa0d6376f193/093624864899.jpg/60x60bb.png\", \"attributes\":{\"height\":\"60\"}}, \n" +
                "{\"label\":\"https://is4-ssl.mzstatic.com/image/thumb/Music122/v4/30/17/1b/30171b77-7d87-bf60-49f1-fa0d6376f193/093624864899.jpg/170x170bb.png\", \"attributes\":{\"height\":\"170\"}}], \"im:itemCount\":{\"label\":\"9\"}, \"im:price\":{\"label\":\"\$7.99\", \"attributes\":{\"amount\":\"7.99\", \"currency\":\"USD\"}}, \"im:contentType\":{\"im:contentType\":{\"attributes\":{\"term\":\"Album\", \"label\":\"Album\"}}, \"attributes\":{\"term\":\"Music\", \"label\":\"Music\"}}, \"rights\":{\"label\":\"A Warner Music Nashville / Elektra Records release, ℗ 2022 Elektra Records LLC\"}, \"title\":{\"label\":\"Leave The Light On - EP - Bailey Zimmerman\"}, \"link\":{\"attributes\":{\"rel\":\"alternate\", \"type\":\"text/html\", \"href\":\"https://music.apple.com/us/album/leave-the-light-on-ep/1644401704?uo=2\"}}, \"id\":{\"label\":\"https://music.apple.com/us/album/leave-the-light-on-ep/1644401704?uo=2\", \"attributes\":{\"im:id\":\"1644401704\"}}, \"im:artist\":{\"label\":\"Bailey Zimmerman\", \"attributes\":{\"href\":\"https://music.apple.com/us/artist/bailey-zimmerman/1551033783?uo=2\"}}, \"category\":{\"attributes\":{\"im:id\":\"6\", \"term\":\"Country\", \"scheme\":\"https://music.apple.com/us/genre/music-country/id6?uo=2\", \"label\":\"Country\"}}, \"im:releaseDate\":{\"label\":\"2022-10-14T00:00:00-07:00\", \"attributes\":{\"label\":\"October 14, 2022\"}}}], \"updated\":{\"label\":\"2023-01-08T12:06:19-07:00\"}, \"rights\":{\"label\":\"Copyright 2008 Apple Inc.\"}, \"title\":{\"label\":\"iTunes Store: Top Albums\"}, \"icon\":{\"label\":\"http://itunes.apple.com/favicon.ico\"}, \"link\":[\n" +
                "{\"attributes\":{\"rel\":\"alternate\", \"type\":\"text/html\", \"href\":\"https://itunes.apple.com/WebObjects/MZStore.woa/wa/viewTop?cc=us&id=1&popId=11\"}}, \n" +
                "{\"attributes\":{\"rel\":\"self\", \"href\":\"https://mzstoreservices-int-st.itunes.apple.com/us/rss/topalbums/limit=2/json\"}}], \"id\":{\"label\":\"https://mzstoreservices-int-st.itunes.apple.com/us/rss/topalbums/limit=2/json\"}}}"


    override suspend fun fetchSongs(limit: Int): ITuneResponse {
        val res = Gson().fromJson(json, ITuneResponse::class.java)
        return res
    }

}