package com.quiqle.quiqlefitness.tools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.quiqle.quiqlefitness.R
import com.quiqle.quiqlefitness.SaveData
import com.facebook.ads.*

class DietChartActivity : AppCompatActivity() {

    private lateinit var saveData: SaveData
    private  lateinit var interstitialAd: InterstitialAd
    private  lateinit  var adView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_chart)
        AudienceNetworkAds.initialize(this);
        adView = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        val adscontainer = findViewById<LinearLayout>(R.id.banner_container)
        adscontainer.addView(adView)
        adView.loadAd()
        interstitialAd = InterstitialAd(this, "612186619649274_612575379610398 ")
        interstitialAd!!.setAdListener(object : InterstitialAdListener {
            override fun onInterstitialDisplayed(p0: Ad?) {

//                val intent = Intent(this@StartActivity,MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }

            override fun onAdClicked(p0: Ad?) {

            }

            override fun onInterstitialDismissed(p0: Ad?) {
                val intent = Intent(this@DietChartActivity, ToolsActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onError(p0: Ad?, p1: AdError?) {
//                val intent = Intent(this@DietChartActivity, ToolsActivity::class.java)
//                startActivity(intent)
//                finish()
            }

            override fun onAdLoaded(p0: Ad?) {

            }

            override fun onLoggingImpression(p0: Ad?) {

            }
        })
        interstitialAd.loadAd()

        //Button
        val btn = findViewById<ImageView>(R.id.btnBack)
        btn.setOnClickListener {
            if(interstitialAd.isAdLoaded)
            {
            interstitialAd.show()
        }else{
                val intent = Intent(this@DietChartActivity, ToolsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onBackPressed() {
        if(interstitialAd.isAdLoaded){
        interstitialAd.show()}
        else
        {
            val intent = Intent(this@DietChartActivity, ToolsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onDestroy() {
        if (interstitialAd != null) {
            interstitialAd!!.destroy()
        }
        if (adView != null) {
            adView.destroy()
        }
        super.onDestroy()
    }
}
