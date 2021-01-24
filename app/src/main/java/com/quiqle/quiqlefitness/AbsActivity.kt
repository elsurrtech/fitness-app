package com.quiqle.quiqlefitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.quiqle.quiqlefitness.abs.*
import com.facebook.ads.*

class AbsActivity : AppCompatActivity() {

    private lateinit var saveData: SaveData
    private  lateinit  var adView: AdView
    private  lateinit  var adView1: AdView
    private  lateinit  var adView2: AdView
    private lateinit var nativeBannerAd: NativeBannerAd
    private lateinit var nativeBannerAd1: NativeBannerAd
    private  lateinit var interstitialAd: InterstitialAd
    private  var Tagclick="level3"

    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs)
        Tagclick=""
        AudienceNetworkAds.initialize(this);
        var nativeAd: NativeAd? = null
        adView = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        adView1 = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        adView2 = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        nativeBannerAd = NativeBannerAd(this, "612186619649274_612574616277141")
        nativeBannerAd1 = NativeBannerAd(this, "612186619649274_612574616277141")
        nativeAd = NativeAd(this,"612186619649274_612187522982517")
        interstitialAd = InterstitialAd(this, "612186619649274_612575379610398")

        interstitialAd!!.setAdListener(object : InterstitialAdListener{
            override fun onInterstitialDisplayed(p0: Ad?) {

//                val intent = Intent(this@StartActivity,MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }

            override fun onAdClicked(p0: Ad?) {

            }

            override fun onInterstitialDismissed(p0: Ad?) {
                if(Tagclick.equals("level3")){
                    val intent = Intent(this@AbsActivity, abs_three::class.java)
                    startActivity(intent)
                    finish()
                }
                else if(Tagclick.equals("level6"))
                {
                    val intent = Intent(this@AbsActivity, abs_six::class.java)
                    startActivity(intent)
                    finish()
                }


            }

            override fun onError(p0: Ad?, p1: AdError?) {
//                if(Tagclick.equals("level3")){
//                    val intent = Intent(this@AbsActivity, abs_three::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                else if(Tagclick.equals("level6"))
//                {
//                    val intent = Intent(this@AbsActivity, abs_six::class.java)
//                    startActivity(intent)
//                    finish()
//                }


            }

            override fun onAdLoaded(p0: Ad?) {

            }

            override fun onLoggingImpression(p0: Ad?) {

            }
        })
        interstitialAd.loadAd()

        val adscontainer = findViewById<LinearLayout>(R.id.banner_container)
        val adscontainer1 = findViewById<LinearLayout>(R.id.banner_container1)
        val adscontainer2 = findViewById<LinearLayout>(R.id.banner_container2)
        adscontainer.addView(adView)
        adscontainer1.addView(adView1)
        adscontainer2.addView(adView2)
        adView.loadAd()
        adView1.loadAd()
        adView2.loadAd()
        // native

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
//nativebanner

        nativeBannerAd.setAdListener(object : NativeAdListener {
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

                if (nativeBannerAd == null || nativeBannerAd != p0) {
                    return;
                }
                // Inflate Native Ad into Container
                var nativeAdLayout: NativeAdLayout
                nativeAdLayout = findViewById(R.id.native_banner_ad_container)

                inflateAd(nativeBannerAd,nativeAdLayout);

            }

            override fun onLoggingImpression(p0: Ad?) {

            }
        } )
        nativeBannerAd1.setAdListener(object : NativeAdListener {
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

                if (nativeBannerAd1 == null || nativeBannerAd1 != p0) {
                    return;
                }
                // Inflate Native Ad into Container
                var nativeAdLayout: NativeAdLayout
                nativeAdLayout = findViewById(R.id.native_banner_ad_container1)

                inflateAd(nativeBannerAd1,nativeAdLayout);

            }

            override fun onLoggingImpression(p0: Ad?) {

            }
        } )
        nativeBannerAd.loadAd()
        nativeBannerAd1.loadAd()
        val absOne = findViewById<CardView>(R.id.absOne)
        absOne.setOnClickListener {
            val intent = Intent(this@AbsActivity, abs_one::class.java)
            startActivity(intent)
            finish()
        }

        val absTwo = findViewById<CardView>(R.id.absTwo)
        absTwo.setOnClickListener {
            val intent = Intent(this@AbsActivity, abs_two::class.java)
            startActivity(intent)
            finish()
        }

        val absThree = findViewById<CardView>(R.id.absThree)
        absThree.setOnClickListener {

            Tagclick="level3"

            if(interstitialAd.isAdLoaded)
            {
                interstitialAd.show()
            }
            else{
                val intent = Intent(this@AbsActivity, abs_three::class.java)
                startActivity(intent)
                finish()
            }
//            if(interstitialAd == null || !interstitialAd!!.isAdLoaded()) {
//
//            }
//            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
//            if(interstitialAd?.isAdInvalidated()!!) {
//                val intent = Intent(this@AbsActivity, abs_three::class.java)
//                startActivity(intent)
//                finish()
//            }
//            // Show the ad
         //   interstitialAd!!.show()
        }

        val absFour = findViewById<CardView>(R.id.absFour)
        absFour.setOnClickListener {
            val intent = Intent(this@AbsActivity, abs_four::class.java)
            startActivity(intent)
            finish()
        }

        val absFive = findViewById<CardView>(R.id.absFive)
        absFive.setOnClickListener {
            val intent = Intent(this@AbsActivity, abs_five::class.java)
            startActivity(intent)
            finish()
        }

        val absSix = findViewById<CardView>(R.id.absSix)
        absSix.setOnClickListener {

            Tagclick="level6"

            if(interstitialAd.isAdLoaded)
            {
                interstitialAd.show()
            }
            else{
                val intent = Intent(this@AbsActivity, abs_six::class.java)
                startActivity(intent)
                finish()
            }
//            if(interstitialAd == null || !interstitialAd!!.isAdLoaded()) {
//                val intent = Intent(this@AbsActivity, abs_six::class.java)
//                startActivity(intent)
//            }
//            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
//            if(interstitialAd?.isAdInvalidated()!!) {
//                val intent = Intent(this@AbsActivity, abs_six::class.java)
//                startActivity(intent)
//            }
//            // Show the ad
//            interstitialAd!!.show()
        }


    }

    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onDestroy() {
        if (adView != null) {
            adView.destroy()
        }
        if (adView1 != null) {
            adView.destroy()
        }
        if (adView2 != null) {
            adView.destroy()
        }
        if (interstitialAd != null) {
            interstitialAd!!.destroy()
        }
        super.onDestroy()
    }
    private fun inflateAd(nativeBannerAd: NativeBannerAd,nativeAdLayout: NativeAdLayout) {

        nativeBannerAd.unregisterView()
        var customAdView: LinearLayout

        customAdView = LayoutInflater.from(this).inflate(R.layout.native_banner_ad_layout,nativeAdLayout,false) as LinearLayout
        nativeAdLayout.addView(customAdView)
        val choicesView = customAdView.findViewById<RelativeLayout>(R.id.ad_choices_container)
        var adChoicesView: AdOptionsView = AdOptionsView(this,nativeBannerAd,nativeAdLayout)
        choicesView.removeAllViews()
        choicesView.addView(adChoicesView,0)
        //get ads views
        val adicon = customAdView.findViewById<AdIconView>(R.id.native_ad_icon)
        val adTitle = customAdView.findViewById<TextView>(R.id.native_ad_title)
//        val adMediaView = customAdView.findViewById<MediaView>(R.id.native_ad_media)
        val nativeadsocialcontent= customAdView.findViewById<TextView>(R.id.native_ad_social_context)
        //  val adBody = customAdView.findViewById<TextView>(R.id.native_ad_body)
        val sponsoredLabel = customAdView.findViewById<TextView>(R.id.native_ad_sponsored_label)
        val nativecalltoaction = customAdView.findViewById<Button>(R.id.native_ad_call_to_action)




        adTitle.text = nativeBannerAd.advertiserName
        //  adBody.text = nativeBannerAd.adBodyText
        nativeadsocialcontent.text=nativeBannerAd.adSocialContext
        nativecalltoaction.visibility = if (nativeBannerAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
        nativecalltoaction.text = nativeBannerAd.adCallToAction
        sponsoredLabel.text = nativeBannerAd.sponsoredTranslation
        val clickableViews: MutableList<View> = ArrayList()
        clickableViews.add(adTitle)
        clickableViews.add(nativecalltoaction)

        nativeBannerAd.registerViewForInteraction(adView, adicon, clickableViews);

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
}
