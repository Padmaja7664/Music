package com.example.myapplication

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer:MediaPlayer
    var totalTime:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer= MediaPlayer.create(this,R.raw.music)
        mediaPlayer.setVolume(1f,1f)
        totalTime=mediaPlayer.duration

        val play=findViewById<ImageView>(R.id.imgplay)
        val pause=findViewById<ImageView>(R.id.imgpause)
        val stop=findViewById<ImageView>(R.id.imgstop)
        val seekBar=findViewById<SeekBar>(R.id.seekBarid)
        play.setOnClickListener{
            mediaPlayer.start()
        }
        pause.setOnClickListener {
            mediaPlayer.pause()
        }
        stop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer.reset()
            mediaPlayer.release()


        }

        //when user changes the time stamp of music,reflect that change

        seekBar.max=totalTime

        seekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        //change the seekbar position based on music

        val handler=Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
                try {
                    seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (exception:java.lang.Exception){
                    seekBar.progress=0
                }
            }


        },0)
    }
}