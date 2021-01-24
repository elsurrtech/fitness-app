package com.quiqle.quiqlefitness

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.quiqle.quiqlefitness.tools.TipsActivity
import com.quiqle.quiqlefitness.tools.ToolsActivity
import com.custom.sliderimage.logic.SliderImage
import com.facebook.ads.*
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var saveData: SaveData
    private  lateinit  var adView: AdView
    private  lateinit  var adView1: AdView
    private lateinit var nativeBannerAd: NativeBannerAd
    private lateinit var nativeBannerAd1: NativeBannerAd
    private  lateinit var interstitialAd: InterstitialAd
    private  val Topic="USER"
    private  var Tagclick=""
    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Tagclick=""
        createChannel("myfcm_channel","channel")
        subscribeTopic()

        AudienceNetworkAds.initialize(this);
        adView = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        adView1 = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        interstitialAd = InterstitialAd(this, "612186619649274_612575379610398")
        nativeBannerAd = NativeBannerAd(this, "612186619649274_612574616277141")
        nativeBannerAd1 = NativeBannerAd(this, "612186619649274_612574616277141")

        interstitialAd!!.setAdListener(object : InterstitialAdListener{
            override fun onInterstitialDisplayed(p0: Ad?) {

//                val intent = Intent(this@StartActivity,MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }

            override fun onAdClicked(p0: Ad?) {

            }

            override fun onInterstitialDismissed(p0: Ad?) {
                if(Tagclick.equals("abs")){
                val intent = Intent(this@MainActivity, AbsActivity::class.java)
                startActivity(intent)
                finish()}
                else if(Tagclick.equals("shoulder"))
                {
                    val intent = Intent(this@MainActivity, ShoulderBack::class.java)
                    startActivity(intent)
                    finish()
                }
                else if(Tagclick.equals("arms"))
                {
                    val intent = Intent(this@MainActivity, ArmsAndChest::class.java)
                    startActivity(intent)
                    finish()
                }
                else if(Tagclick.equals("legs"))
                {val intent = Intent(this@MainActivity, Legs::class.java)
                    startActivity(intent)
                    finish()
                }

            }

            override fun onError(p0: Ad?, p1: AdError?) {
//                if(Tagclick.equals("abs")){
//                    val intent = Intent(this@MainActivity, AbsActivity::class.java)
//                    startActivity(intent)
//                    finish()}
//                else if(Tagclick.equals("shoulder"))
//                {
//                    val intent = Intent(this@MainActivity, ShoulderBack::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                else if(Tagclick.equals("arms"))
//                {
//                    val intent = Intent(this@MainActivity, ArmsAndChest::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                else if(Tagclick.equals("legs"))
//                {val intent = Intent(this@MainActivity, Legs::class.java)
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
        val adscontainer = findViewById<LinearLayout>(R.id.banner_container)
        val adscontainer2 = findViewById<LinearLayout>(R.id.banner_container2)
      adscontainer.addView(adView)
        adscontainer2.addView(adView1)
        adView.loadAd()
        adView1.loadAd()


        val btnSettings = findViewById<ImageView>(R.id.settings)
        btnSettings.setOnClickListener {
            val i = Intent(this,SettingsActivity::class.java)
            startActivity(i)
            finish()
        }

        tips.setOnClickListener {
            val i = Intent(this,TipsActivity::class.java)
            startActivity(i)
            finish()
        }

        toolBar.setOnClickListener {
            val i = Intent(this,ToolsActivity::class.java)
            startActivity(i)
            finish()
        }

        //Slider
        val slider =  findViewById<SliderImage>(R.id.slider)
        val images = listOf("https://manofmany.com/wp-content/uploads/2019/03/10-Best-Core-Exercises-for-Men-Abs-2.jpg",
            "https://i1.wp.com/healtrick.com/wp-content/uploads/2020/03/best-abs-workouts-for-men.png",
            "https://www.jackedfactory.com/wp-content/uploads/2019/05/woman-athlete-using-the-leg-press.jpg",
            "https://cdn2.coachmag.co.uk/sites/coachmag/files/styles/16x9_746/public/2018/10/legs-workout.jpg",
            "https://media1.popsugar-assets.com/files/thumbor/KfiZ86lnYgfQReCP_TRNSsPhuMA/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2019/09/25/912/n/1922729/e22fa0d15d8bd3d2b16cc2.42724899_/i/do-you-have-lose-weight-before-you-gain-muscle.jpg"
        )

        slider.setItems(images)
        slider.addTimerToSlide(1500)
        slider.getIndicator()

        //button Abs
        val btnAbs =  findViewById<Button>(R.id.btnAbs)
        btnAbs.setOnClickListener {
//            val intent = Intent(this, AbsActivity::class.java)
//            startActivity(intent)
//            finish()
            Tagclick="abs"

            if(interstitialAd.isAdLoaded){
                interstitialAd.show()
            }
            else{
                val intent = Intent(this, AbsActivity::class.java)
                startActivity(intent)
            }
//            if(interstitialAd == null || !interstitialAd!!.isAdLoaded()) {
//                val intent = Intent(this, AbsActivity::class.java)
//                startActivity(intent)
//            }
//            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
//            if(interstitialAd?.isAdInvalidated()!!) {
//                val intent = Intent(this, AbsActivity::class.java)
//                startActivity(intent)
//            }
//            // Show the ad
//            interstitialAd!!.show()
        }

        val btnShoulderBack =  findViewById<Button>(R.id.btnShoulder)
        btnShoulderBack.setOnClickListener {
//            val intent = Intent(this, ShoulderBack::class.java)
//            startActivity(intent)
//            finish()
            Tagclick="shoulder"

            if(interstitialAd.isAdLoaded){
                interstitialAd.show()
            }
            else{
                val intent = Intent(this, ShoulderBack::class.java)
                startActivity(intent)
            }

//            if(interstitialAd == null || !interstitialAd!!.isAdLoaded()) {
//                val intent = Intent(this, ShoulderBack::class.java)
//                startActivity(intent)
//            }
//            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
//            if(interstitialAd?.isAdInvalidated()!!) {
//                val intent = Intent(this, ShoulderBack::class.java)
//                startActivity(intent)
//            }
//            // Show the ad
//
//            interstitialAd!!.show()
        }

        val btnArmsAndChest =  findViewById<Button>(R.id.btnArms)
        btnArmsAndChest.setOnClickListener {
//            val intent = Intent(this, ArmsAndChest::class.java)
//            startActivity(intent)
//            finish()
            Tagclick="arms"

            if(interstitialAd.isAdLoaded){
                interstitialAd.show()
            }
            else{
                val intent = Intent(this, ArmsAndChest::class.java)
                startActivity(intent)
            }
//            if(interstitialAd == null || !interstitialAd!!.isAdLoaded()) {
//                val intent = Intent(this, ArmsAndChest::class.java)
//                startActivity(intent)
//            }
//            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
//            if(interstitialAd?.isAdInvalidated()!!) {
//                val intent = Intent(this, ArmsAndChest::class.java)
//                startActivity(intent)
//            }
//            // Show the ad
//            interstitialAd!!.show()
        }

        val btnLegs =  findViewById<Button>(R.id.btnLegs)
        btnLegs.setOnClickListener {
//            val intent = Intent(this, Legs::class.java)
//            startActivity(intent)
//            finish()
            Tagclick="legs"

            if(interstitialAd.isAdLoaded){
                interstitialAd.show()
            }
            else{
                val intent = Intent(this, Legs::class.java)
                startActivity(intent)
            }
//            if(interstitialAd == null || !interstitialAd!!.isAdLoaded()) {
//                val intent = Intent(this, Legs::class.java)
//                startActivity(intent)
//            }
//            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
//            if(interstitialAd?.isAdInvalidated()!!) {
//                val intent = Intent(this, Legs::class.java)
//                startActivity(intent)
//            }
//            // Show the ad
//            interstitialAd!!.show()
        }

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

    private fun subscribeTopic() {

        FirebaseMessaging.getInstance().subscribeToTopic(Topic)
            .addOnCompleteListener { task ->
                var message = "Subscribed"

                if (!task.isSuccessful) {
                    message = "Not Subscribed"
                }
//                Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
            }

    }
    private fun createChannel(channelId: String, channelName: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                channelId,
                channelName,

                NotificationManager.IMPORTANCE_HIGH
            )

                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notification Channel"

            val notificationManager = getSystemService(
                NotificationManager::class.java
            )

            notificationManager.createNotificationChannel(notificationChannel)

        }

    }
    override fun onDestroy() {
        if (adView != null) {
            adView.destroy()
        }
        if (adView1 != null) {
            adView.destroy()
        }
        if (interstitialAd != null) {
            interstitialAd!!.destroy()
        }
        super.onDestroy()
    }
    override fun onBackPressed() {

        val inflater = layoutInflater
        val inflate_view = inflater.inflate(R.layout.custom_alert_dialog,null)

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setView(inflate_view)
        alertDialog.setCancelable(false)
        alertDialog.setNeutralButton("Rate us"){ dialog, which->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.quiqle.quiqlefitness")))
        }
        alertDialog.setNegativeButton("Quit"){dialog, which ->
            finishAffinity()

        }
        alertDialog.setPositiveButton("Continue"){dialog, which ->  }

        val dialog = alertDialog.create()
        dialog.show()

    }
}
