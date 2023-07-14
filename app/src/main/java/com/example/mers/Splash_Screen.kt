package com.example.mers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import java.util.Timer
import java.util.TimerTask
import kotlin.math.log

class Splash_Screen : AppCompatActivity() {

    private lateinit var logoimage: ImageView
    private lateinit var timer: Timer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        logoimage = findViewById(R.id.iv_zeallogo)
        logoimage.alpha = 0f
        logoimage.animate().setDuration(1500).alpha( 1f).withEndAction {
            val i = Intent( this,loginactivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
        timer= Timer()
        timer.schedule(object : TimerTask(){
            override fun run(){
                val intent = Intent(this@Splash_Screen,loginactivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)







    }

}



