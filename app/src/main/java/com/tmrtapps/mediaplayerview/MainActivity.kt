package com.tmrtapps.mediaplayerview

import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.tmrtapps.mediaplayerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storagePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

            var allPermissionsAllowed = true

            permissions.entries.forEach {
                if (!it.value) {
                    allPermissionsAllowed = false
                }
            }

            if (allPermissionsAllowed) {
                fetchSongs()
            }
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            fetchSongs()

        } else {

            storagePermissionLauncher.launch(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
        }
    }

    private fun fetchSongs() {

        val uriLibrary: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf (
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATE_MODIFIED,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DURATION
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} = ? AND ${MediaStore.Audio.Media.TITLE} NOT LIKE ? AND ${MediaStore.Audio.Media.TITLE} NOT LIKE ?"

        val selectionArgs = mutableListOf("1", "AUD%", "PTT%")

        val sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER

        val list: MutableList<Audio> = mutableListOf()

        contentResolver.query(uriLibrary, projection, selection, selectionArgs.toTypedArray(), sortOrder)?.use { cursor ->

            while (cursor.moveToNext()) {

                val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
                val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val albumIdIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)

                val data = cursor.getString(dataIndex)
                val title = cursor.getString(titleIndex)
                val artist = cursor.getString(artistIndex)
                val albumId = cursor.getLong(albumIdIndex)

                val albumArtUri = ContentUris.withAppendedId("content://media/external/audio/albumart".toUri(), albumId).toString()

                val mediaAudioItem = Audio(data, albumArtUri, title, artist)

                list.add(mediaAudioItem)
            }
        }

        list.add(2, Audio("", null, "error", "error"))


        binding.m.setOnClickListener {
            binding.mediaPlayerView.setDataSource(list)
            binding.mediaPlayerView.prepare(0, false)
        }

        binding.y.setOnClickListener {
            binding.mediaPlayerView.pause()
        }

        binding.k.setOnClickListener {
            binding.mediaPlayerView.play()
        }

        binding.h.setOnClickListener {
            binding.mediaPlayerView.pause(true)
        }

        binding.j.setOnClickListener {
            binding.mediaPlayerView.next()
        }

        binding.z.setOnClickListener {
            binding.mediaPlayerView.prev()
        }

        binding.x.setOnClickListener {
            binding.mediaPlayerView.release()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mediaPlayerView.release()
    }
}
