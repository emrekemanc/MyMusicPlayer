package com.example.yeni.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import com.example.yeni.databinding.ActivityAnaSayfaBinding
import com.example.yeni.view.*

var runnable : Runnable = Runnable {  }
var handler = Handler(Looper.myLooper()!!)


class seekBar(val binding: ActivityAnaSayfaBinding,val context: Context) {
    fun sekbar(){
        runnable=object :Runnable{
            override fun run() {
                if (play.currentPosition>= play.duration-200){
                    if (numara<lis.size-1){
                        numara++
                    }
                    else if (numara== lis.size-1){
                        numara=0
                    }
                    geriİleri(context,binding).geriileri()
                   handler.removeCallbacks(runnable)
                }
                binding.seekBar.progress= play.currentPosition
                handler.postDelayed(runnable,100)
            }
        }
        handler.post(runnable)

    }



    fun seekbar(){
        binding.seekBar.max= play.duration
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(p2){ play.seekTo(p1)}
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }
}