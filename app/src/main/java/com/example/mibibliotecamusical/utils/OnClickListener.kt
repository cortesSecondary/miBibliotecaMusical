package com.example.mibibliotecamusical.utils

import com.example.mibibliotecamusical.entities.Playlist
import com.example.mibibliotecamusical.entities.Song

interface OnClickListener
{
    fun addSong(song: Song)
    fun addPlaylist(playlist: Playlist)
}