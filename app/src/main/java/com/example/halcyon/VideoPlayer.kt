package com.example.halcyon

import android.net.Uri.parse
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import java.net.URI

class VideoPlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        val backBtn = findViewById<Button>(R.id.backToAudio)
        val videoView = findViewById<VideoView>(R.id.videoView)
        var mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)


        val uri: Uri? = parse("android.resource://"+packageName+"/"+R.raw.nandemonaiya)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}