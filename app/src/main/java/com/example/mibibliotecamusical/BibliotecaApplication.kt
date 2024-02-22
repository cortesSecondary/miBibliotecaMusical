package com.example.mibibliotecamusical

import android.app.Application

class BibliotecaApplication: Application()
{
    companion object
    {
        var userID: Int = 0
        var songID: Int = 0
        val albumImages = listOf(
            "https://discmedi.com/portadas/SON62972.jpg?w=600",
            "https://m.media-amazon.com/images/I/61pO8rENBML._UF894,1000_QL80_.jpg",
            "https://m.media-amazon.com/images/I/61pAyUQ5JBS._UF894,1000_QL80_.jpg",
            "https://f4.bcbits.com/img/a0366365091_5.jpg",
            "https://cloud10.todocoleccion.online/musica-cds/tc/2023/03/04/13/395933009_tcimg_FFC80A18.jpg",
            "https://m.media-amazon.com/images/S/pv-target-images/e22fa7df30990d68db9b27c3635274a1a071ed390c36d11e8873797bcb7e8e37.jpg",
            "https://upload.wikimedia.org/wikipedia/en/3/38/Jose_J_Balvin.jpg",
            "https://upload.wikimedia.org/wikipedia/en/1/1a/J_Balvin_-_Colores.png",
            "https://m.media-amazon.com/images/I/A1JljSv+c1L._UF894,1000_QL80_.jpg",
            "https://i.discogs.com/Isq4jUq3rO24oapW1uRA2DUkweVlglRn-ZPkW0yEvQM/rs:fit/g:sm/q:90/h:523/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEyNDEz/NzQyLTE1MzQ3OTA4/MTktODA5My5qcGVn.jpeg",
            "https://m.media-amazon.com/images/I/61f6QfCvDNL._UF894,1000_QL80_.jpg",
            "https://upload.wikimedia.org/wikipedia/en/c/c7/Chris_Brown_-_11-11_Cover.jpeg",
            "https://m.media-amazon.com/images/I/91NqTHVgqbL._UF894,1000_QL80_.jpg",
            "https://www.lahiguera.net/musicalia/artistas/bad_bunny/disco/10999/bad_bunny_el_ultimo_tour_del_mundo-portada.jpg",
            "https://images.prismic.io/milanote/df7eeb83a07162b45ac2e882cac055de9411054a_cover.jpg",
            "https://i.discogs.com/os2UjXrfq9ieL9ipPuZueSIp5dlb5x30EaLf7nI5BBs/rs:fit/g:sm/q:90/h:499/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTE1NTc2/OTU2LTE1OTM5MTY2/NTUtNDA0My5qcGVn.jpeg",
            "https://m.media-amazon.com/images/I/51QeyC5laUL._UF894,1000_QL80_.jpg",
            "https://cdns-images.dzcdn.net/images/cover/61ee23bd1a167ab21ac27df7d05bf8eb/264x264.jpg",
            "https://img.apmcdn.org/fa179548a8b1da90522a386566cd5494b8ecafaf/square/e0c8f5-20130822-tim-the-replacements.jpg",
            "https://m.media-amazon.com/images/I/81Cx2XAX3iS._AC_UF894,1000_QL80_.jpg",
            "https://cdns-images.dzcdn.net/images/cover/5af562df591ef8a5705725cebcab56fa/500x500.jpg",
            "https://cdns-images.dzcdn.net/images/cover/a9dd3aa63cfaa8dc25a4c211dbb5c6fe/500x500.jpg",
            "https://cdns-images.dzcdn.net/images/cover/664cd2e671f05f3f8f1e0bbd710e082d/500x500.jpg",
            "https://universalmusiconline.es/cdn/shop/products/impronta.jpg",
            "https://m.media-amazon.com/images/I/71XtzxHGPLL._UF894,1000_QL80_.jpg",
            "https://akamai.sscdn.co/letras/360x360/albuns/a/f/4/f/640581530021040.jpg"
        )
        val podcastImages = listOf("https://is2-ssl.mzstatic.com/image/thumb/Podcasts116/v4/0e/02/37/0e023722-5306-f754-3104-536717589b55/mza_16291245181013556057.jpg/600x600bb.jpg",
            "https://i.scdn.co/image/ab67616d0000b273a578ec0d3f044f72cf142505",
            "https://i.scdn.co/image/ab6765630000ba8a45177fa88b1fd3777e7f0e0b",
            "https://i.scdn.co/image/ab6765630000ba8ae4ee1c49deaf4d71f063eae8",
            "https://i.scdn.co/image/ab6765630000ba8ad2b58226776c369150e52b3e",
            "https://static-1.ivoox.com/canales/4/1/1/8/2121640178114_LG.jpg",
            "https://entiendetumente.info/wp-content/uploads/2023/10/300x250.jpg",
            "https://elterrat.com/wp-content/uploads/2022/06/nadie-sabe-nada-cover-podcast.jpg",
            "https://cachedimages.podchaser.com/256x256/aHR0cHM6Ly9pLnNjZG4uY28vaW1hZ2UvYWI2NzY1NjMwMDAwYmE4YTQ3MTJjZmM0NzhkYjZiNzlhNTRlZDY0MA%3D%3D/aHR0cHM6Ly93d3cucG9kY2hhc2VyLmNvbS9pbWFnZXMvbWlzc2luZy1pbWFnZS5wbmc%3D",
            "https://is1-ssl.mzstatic.com/image/thumb/Podcasts112/v4/a4/74/fc/a474fccd-747d-6a18-6ffb-f2b051fd7678/mza_545810402022571440.jpg/1200x1200bb.jpg",
            "https://pbs.twimg.com/profile_images/1635165667551854592/j9zUNfWt_400x400.jpg",
            "https://static.prod.radio-api.net/levantate-ok/91d024552303cfd8dff00e04211bdef6/logo_1200x1200.png",
            "https://ethic.es/wp-content/uploads/2022/03/Podcast-Leroy-Merlin-1280x720.png",
            "https://static.prod.radio-api.net/como-si-nadie-escuchara/48532c847e7851434db1dd8c31b77a85/logo_300x300.png",
            "https://www.omnycontent.com/d/playlist/2446592a-b80e-4d28-a4fd-ae4c0140ac11/172ad2c5-9214-4b21-85fe-aeaa01143b70/58c58f5a-82b3-4be2-aa54-aeaa01143b82/image.jpg?t=1695043297&size=Medium",
            "https://i.scdn.co/image/ab6765630000ba8a53b7f03ff9531aeee50620bb",
            "https://is1-ssl.mzstatic.com/image/thumb/Podcasts112/v4/e5/6b/97/e56b97b2-c924-fb9d-68cc-681872077cb8/mza_16139685102553105689.jpg/1200x1200bb.jpg",
            "https://www.pikaramagazine.com/wp-content/uploads/2023/10/malditobollodrama-temp3.png",
            "https://static-1.ivoox.com/audios/5/e/1/5/5e1532273d19f133b3e0ba330aaea207_LG.jpg",
            "https://imgv2-2-f.scribdassets.com/img/word_document/507564666/original/d2058e3656/1705399728?v=1")
        val playlistImages = listOf("https://d1csarkz8obe9u.cloudfront.net/posterpreviews/playlist-cover-design-template-7dd4f20145b44bab7da20e33ecbcada6_screen.jpg?ts=1698901245",
            "https://marketplace.canva.com/EAEdft48JIs/1/0/1600w/canva-orange-skyline-tumblr-aesthetic-love-songs-playlist-cover-mCNRRGaWFgU.jpg",
            "https://marketplace.canva.com/EAEdfw3xPPc/1/0/1600w/canva-pink-sunset-tumblr-aesthetic-love-songs-playlist-cover-IeYIUu1iRHQ.jpg",
            "https://marketplace.canva.com/EAEdeiU-IeI/1/0/1600w/canva-purple-and-red-orange-tumblr-aesthetic-chill-acoustic-classical-lo-fi-playlist-cover-jGlDSM71rNM.jpg",
            "https://mir-s3-cdn-cf.behance.net/project_modules/hd/f5a34e108782021.5fc5820ec88bf.png",
            "https://pics.craiyon.com/2023-06-02/0c6f41f999864ad6928ec7a211a905b8.webp",
            "https://mir-s3-cdn-cf.behance.net/project_modules/hd/137625112577745.60177189ef503.png",
            "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/hip-hop-spotify-playlist-cover-design-template-eb9d61a39fd0772fd8f6774a228a0cf0_screen.jpg?ts=1685333804")
    }

    override fun onCreate()
    {
        super.onCreate()
    }
}