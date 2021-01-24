package com.quiqle.quiqlefitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders
import com.onesignal.OneSignal

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val title = findViewById<TextView>(R.id.t1)
        val i1 = findViewById<CircularFillableLoaders>(R.id.i1)
        val t2 = findViewById<TextView>(R.id.t2)
        val t3 =findViewById<TextView>(R.id.t3)

        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()
        //declare animation
        val anim1 = AnimationUtils.loadAnimation(this,R.anim.splash_one)
        val anim2 = AnimationUtils.loadAnimation(this,R.anim.splash_three)
        val anim3 = AnimationUtils.loadAnimation(this,R.anim.splash_four)
        val anim4 = AnimationUtils.loadAnimation(this,R.anim.splash_two)

        //set the animation
        title.startAnimation(anim1)
        t2.startAnimation(anim2)
        t3.startAnimation(anim3)
        i1.startAnimation(anim4)
        i1.setProgress(80)

        //progress bar
        val prgBarHorizontal = findViewById<ProgressBar>(R.id.prgBar)
        var barStatus = 0
        Thread(Runnable {
            while (barStatus<100){
                barStatus+=1

                try {
                    Thread.sleep(20)
                    prgBarHorizontal.setProgress(barStatus)
                }catch (exp: InterruptedException){
                    exp.printStackTrace()
                }
            }
        }).start()



        val SPLASH_TIME_OUT = 2000
        val homeIntent = Intent(this@SplashScreen, MainActivity::class.java)
        Handler().postDelayed({
            //Do some stuff here, like implement deep linking
            startActivity(homeIntent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}
