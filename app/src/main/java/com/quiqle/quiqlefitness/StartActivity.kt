package com.quiqle.quiqlefitness

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {

    lateinit var finish: Button
    private  lateinit var interstitialAd: InterstitialAd
    private  lateinit  var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //incoming data from intents
        val bundle = intent.extras
        val new_gifs = bundle!!.getIntArray("gifs")
        val new_txts = bundle.getStringArray("name")
        val new_desc = bundle.getStringArray("desc")


        var current_value=0
        val final_value = new_gifs?.size
        setContentView(R.layout.activity_start)
        AudienceNetworkAds.initialize(this);
        adView = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        val adscontainer = findViewById<LinearLayout>(R.id.banner_container)
        adscontainer.addView(adView)
        adView.loadAd()
//
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
                val intent = Intent(this@StartActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onError(p0: Ad?, p1: AdError?) {
//                val intent = Intent(this@StartActivity,MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }

            override fun onAdLoaded(p0: Ad?) {

            }

            override fun onLoggingImpression(p0: Ad?) {

            }
        })


        var barstatus=(100 / final_value!!)

        val fragment= StartActivityFragment.newInstance(new_gifs[0], new_txts?.get(0),new_desc?.get(0));
        loadFragment(fragment)
        val buttonnext =findViewById<Button>(R.id.buttonNext);
        val buttonfinish =findViewById<Button>(R.id.buttonfinish);

        val progressBar =findViewById<ProgressBar>(R.id.p1)
        progressBar.progress = barstatus
        buttonfinish.setOnClickListener{

            congrats()

        }

        btnBack.setOnClickListener{
                onBackPressed()
        }
        buttonnext.setOnClickListener{

            if(current_value == (final_value-2)) {
                val fragment= StartActivityFragment.newInstance(new_gifs[final_value-1], new_txts?.get(final_value-1),new_desc?.get(final_value-1));
                loadFragment(fragment)
                progressBar.progress = 100
                buttonnext.visibility=View.GONE
                textNext.visibility=View.GONE
                buttonfinish.visibility=View.VISIBLE
                playAudio(R.raw.bell)
            }
            else if (current_value < final_value-2 ) {
                val fragment= StartActivityFragment.newInstance(new_gifs[current_value+1], new_txts?.get(current_value+1),new_desc?.get(current_value+1));
                loadFragment(fragment)
                current_value++
                barstatus+=(100 / final_value)
                progressBar.progress = barstatus
                playAudio(R.raw.bell)
            }

        }

        textNext.setOnClickListener {
            if(current_value == (final_value-2)) {
                val fragment= StartActivityFragment.newInstance(new_gifs[final_value-1], new_txts?.get(final_value-1),new_desc?.get(final_value-1));
                loadFragment(fragment)
                progressBar.progress = 100
                buttonnext.visibility=View.GONE
                textNext.visibility=View.GONE
                buttonfinish.visibility=View.VISIBLE
                playAudio(R.raw.bell)
            }
            else if (current_value < final_value-2 ) {
                val fragment= StartActivityFragment.newInstance(new_gifs[current_value+1], new_txts?.get(current_value+1),new_desc?.get(current_value+1));
                loadFragment(fragment)
                current_value++
                barstatus+=(100 / final_value)
                progressBar.progress = barstatus
                playAudio(R.raw.bell)
            }
        }



    }
    private fun loadFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager.beginTransaction().replace(R.id.f1,fragment)
        fragmentManager.commit()
    }

    override fun onBackPressed() {

        val inflater = layoutInflater
        val inflate_view = inflater.inflate(R.layout.custom_alert_dialog,null)

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setView(inflate_view)
        alertDialog.setCancelable(false)

        alertDialog.setNegativeButton("Quit"){dialog, which ->
            interstitialAd.loadAd()
            if(interstitialAd.isAdLoaded)
            {
                interstitialAd.show()
            }
            else{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
//            if(interstitialAd == null || !interstitialAd!!.isAdLoaded()) {
//                val intent = Intent(this,MainActivity::class.java)
//                startActivity(intent)
//            }
//            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
//            if(interstitialAd?.isAdInvalidated()!!) {
//                val intent = Intent(this,MainActivity::class.java)
//                startActivity(intent)
//            }
//            // Show the ad
//            interstitialAd!!.show()

        }
        alertDialog.setPositiveButton("Continue"){dialog, which ->  }

        val dialog = alertDialog.create()
        dialog.show()

    }

    fun playAudio(i:Int){
        val player: MediaPlayer? = MediaPlayer.create(this,i)
        player?.start()
    }

    public fun congrats(){
        val i = Intent(this,CongratsActivity::class.java)
        startActivity(i)
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
