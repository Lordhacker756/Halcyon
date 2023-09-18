package com.example.halcyon

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var seekBar: SeekBar
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val play = findViewById<FloatingActionButton>(R.id.play)
        val pause = findViewById<FloatingActionButton>(R.id.pause)
        val stop = findViewById<FloatingActionButton>(R.id.stop)

        seekBar = findViewById(R.id.music_progress)
        handler = Handler(Looper.getMainLooper())

        play.setOnClickListener {
            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this, R.raw.music)
                initializeSeekBar()
            }
            mediaPlayer?.start()
            Toast.makeText(this, "Playing", Toast.LENGTH_LONG)
        }

        pause.setOnClickListener {
            mediaPlayer?.pause()
            Toast.makeText(this, "Paused", Toast.LENGTH_LONG)
        }

        stop.setOnClickListener {
            Toast.makeText(this, "Stopped", Toast.LENGTH_LONG)
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()

            mediaPlayer = null
            handler.removeCallbacks(runnable)
            seekBar.progress = 0
        }
    }
    private fun initializeSeekBar(){
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        var playedTime = findViewById<TextView>(R.id.start)
        var remainingTime = findViewById<TextView>(R.id.end)

        seekBar.max = mediaPlayer!!.duration
        runnable = Runnable {
            seekBar.progress = mediaPlayer!!.currentPosition
            val played = mediaPlayer!!.currentPosition / 1000
            val total = mediaPlayer!!.duration / 1000
            val rem = total - played

            playedTime.text =  getTime(played)
            remainingTime.text = getTime(rem)

            handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)
    }

    fun getTime(time: Int): String {
        var minutes = time / 60;
        var seconds = time - (minutes * 60)
        return if (seconds < 10) "$minutes:0$seconds" else "$minutes:$seconds"
    }
}