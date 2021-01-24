package com.quiqle.quiqlefitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_congrats.*

class CongratsActivity : AppCompatActivity() {
    private  lateinit var interstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congrats)
        AudienceNetworkAds.initialize(this);
        interstitialAd = InterstitialAd(this, "612186619649274_612575379610398")
        interstitialAd!!.setAdListener(object : InterstitialAdListener {
            override fun onInterstitialDisplayed(p0: Ad?) {

//                val intent = Intent(this@StartActivity,MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }

            override fun onAdClicked(p0: Ad?) {

            }

            override fun onInterstitialDismissed(p0: Ad?) {
                val i = Intent(this@CongratsActivity,MainActivity::class.java)
                startActivity(i)
                finish()
            }

            override fun onError(p0: Ad?, p1: AdError?) {
//                val i = Intent(this@CongratsActivity,MainActivity::class.java)
//                startActivity(i)
//                finish()
            }

            override fun onAdLoaded(p0: Ad?) {

            }

            override fun onLoggingImpression(p0: Ad?) {

            }
        })
        interstitialAd.loadAd()

        btnGo.setOnClickListener {
            if(interstitialAd.isAdLoaded)
            {
                interstitialAd.show()
            }
            else{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

    }

    override fun onBackPressed() {
        if(interstitialAd.isAdLoaded)
        {
            interstitialAd.show()
        }
        else{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onDestroy() {
        if (interstitialAd != null) {
            interstitialAd!!.destroy()
        }
        super.onDestroy()
    }
}
