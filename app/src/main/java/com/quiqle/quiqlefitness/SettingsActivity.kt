package com.quiqle.quiqlefitness

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private var switch: Switch? = null
    private lateinit var saveData: SaveData



    override fun onCreate(savedInstanceState: Bundle?) {

        //AppTheme
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }

        //OnCreate ContentView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        AudienceNetworkAds.initialize(this);
        //ads

        var nativeAd: NativeAd? = null
        nativeAd = NativeAd(this,"612186619649274_612187522982517")
        nativeAd.setAdListener(object : NativeAdListener{
            override fun onAdClicked(p0: Ad?) {

            }

            override fun onMediaDownloaded(p0: Ad?) {

            }

            override fun onError(p0: Ad?, p1: AdError?) {
                if (p1 != null) {
                    Log.e("TAG", "Native ad failed to load: " + p1.getErrorMessage())
                };
            }

            override fun onAdLoaded(p0: Ad?) {

                if (nativeAd == null || nativeAd != p0) {
                    return;
                }
                // Inflate Native Ad into Container
                inflateAd(nativeAd);

            }

            override fun onLoggingImpression(p0: Ad?) {

            }
        } )
        nativeAd.loadAd()


        //switch for on/off
        switch = findViewById<View>(R.id.on_off) as Switch?
        if (saveData.loadDarkModeState() == true){
            switch!!.isChecked = true
        }

        //On click ON OFF
        switch!!.setOnCheckedChangeListener{ _, isChecked->

            if(isChecked){
                saveData.setDarkModeState(true)
                restartApplication()
            }else{
                saveData.setDarkModeState(false)
                restartApplication()
            }

        }

        //Button
        val btn = findViewById<ImageView>(R.id.btnBack)
        btn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        cardPrivacy.setOnClickListener {

            val url = "https://drive.google.com/file/d/1hPbFpnyP5v1SZtHKfNHRTsaGfJb0T0eu/view?usp=sharing"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        quiqle.setOnClickListener{
            val url = "http://quiqle.in/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun inflateAd(nativeAd: NativeAd){
        nativeAd.unregisterView()
        var customAdView: LinearLayout
        var nativeAdLayout: NativeAdLayout
        nativeAdLayout = findViewById(R.id.nativeAdLayout)

        customAdView = LayoutInflater.from(this).inflate(R.layout.native_ad_layout,nativeAdLayout,false) as LinearLayout
        nativeAdLayout.addView(customAdView)
        val choicesView = customAdView.findViewById<LinearLayout>(R.id.ad_choices_container)
        var adChoicesView: AdOptionsView = AdOptionsView(this,nativeAd,nativeAdLayout)
        choicesView.removeAllViews()
        choicesView.addView(adChoicesView,0)
        //get ads views
        val adicon = customAdView.findViewById<AdIconView>(R.id.native_ad_icon)
        val adTitle = customAdView.findViewById<TextView>(R.id.native_ad_title)
        val adMediaView = customAdView.findViewById<MediaView>(R.id.native_ad_media)
        val nativeadsocialcontent= customAdView.findViewById<TextView>(R.id.native_ad_social_context)
        val adBody = customAdView.findViewById<TextView>(R.id.native_ad_body)
        val sponsoredLabel = customAdView.findViewById<TextView>(R.id.native_ad_sponsored_label)
        val nativecalltoaction = customAdView.findViewById<Button>(R.id.native_ad_call_to_action)




        adTitle.text = nativeAd.advertiserName
        adBody.text = nativeAd.adBodyText
        nativeadsocialcontent.text=nativeAd.adSocialContext
        nativecalltoaction.visibility = if (nativeAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
        nativecalltoaction.text = nativeAd.adCallToAction
        sponsoredLabel.text = nativeAd.sponsoredTranslation
        val clickableViews: MutableList<View> = ArrayList()
        clickableViews.add(adTitle)
        clickableViews.add(nativecalltoaction)

        nativeAd.registerViewForInteraction(
            customAdView,
            adMediaView,
            adicon,
            clickableViews);


        }

    private fun restartApplication(){
        val i  = Intent(applicationContext, SettingsActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onBackPressed() {
        val i  = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }
}
