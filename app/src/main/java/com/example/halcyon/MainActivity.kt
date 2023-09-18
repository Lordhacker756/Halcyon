package com.example.halcyon

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        val play = findViewById<FloatingActionButton>(R.id.play)
        val pause = findViewById<FloatingActionButton>(R.id.pause)
        val stop = findViewById<FloatingActionButton>(R.id.stop)

        play.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, R.raw.music)
            mediaPlayer!!.start()
            Toast.makeText(this, "Playing", Toast.LENGTH_LONG)
        }

        pause.setOnClickListener {
            mediaPlayer!!.pause()
            Toast.makeText(this, "Paused", Toast.LENGTH_LONG)
        }

        pause.setOnClickListener {
            Toast.makeText(this, "Stopped", Toast.LENGTH_LONG)
            mediaPlayer!!.stop()
            mediaPlayer = null
        }
    }
}