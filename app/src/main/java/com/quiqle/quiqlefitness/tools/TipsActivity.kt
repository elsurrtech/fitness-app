package com.quiqle.quiqlefitness.tools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.quiqle.quiqlefitness.MainActivity
import com.quiqle.quiqlefitness.R
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_tips.*
import java.util.*

class TipsActivity : AppCompatActivity() {
    private  lateinit  var adView: AdView
    private  lateinit var interstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)
        AudienceNetworkAds.initialize(this);
        adView = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        val adscontainer = findViewById<LinearLayout>(R.id.banner_container)
        adscontainer.addView(adView)
        adView.loadAd()
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
                val i = Intent(this@TipsActivity,MainActivity::class.java)
                startActivity(i)
                finish()
            }

            override fun onError(p0: Ad?, p1: AdError?) {
//                val i = Intent(this@TipsActivity,MainActivity::class.java)
//                startActivity(i)
//                finish()
            }

            override fun onAdLoaded(p0: Ad?) {

            }

            override fun onLoggingImpression(p0: Ad?) {

            }
        })
        interstitialAd.loadAd()


        // yha array list paste kr diyo apni tips vaali jo tere pass hai aur iska ui acha kr diyo
        val tips = arrayOf("Muscle growth requires burning many calories. If you are not eating much, there will not be enough calories to provide energy for your body, and your body will consume protein instead of calories. However, protein reduction is not helpful for building muscles.",
            "Exercising several muscles at the same time can help develop your muscular endurance, so you will be able to lift heavier weights, which will trigger more muscle growth. We have equipped you with compound exercises in the workout plan. Follow our plan for better muscle growth!",
            "Protein is made of amino acids, and is the foundation of your muscles and your body. Without amino acids, it's impossible for you to build, repair or even maintain muscle tissue. Your recommended daily protein intake is 0.22% of your body weight. You can get protein from lean meats, salmon, soybean, milk, and eggs.",
            "Growth hormones and testosterone are released while you're sleeping, helping your muscles to recover. Sleep at least 7.5 hours a night. You might like to play smartphone before sleeping now, but blue light will inhibit the secretion of melatonin, making it hard to fall asleep. Shift your screen color to get a better night's sleep. We've chosen the top rated (4.8) app for you.",
            "You won't get results without effort. Do not try to have a body like a celebrity’s in a short time, because building muscles takes time. Focusing on improving yourself is more important. You will be more confident to continue and persist.",
            "Our workout plan is also helpful to teenagers and can help them build more muscle. As they gain more muscles, their metabolism will improve, which can prevent them from gaining fat. Meanwhile, our workout can also keep bones strong, helping teenagers grow taller faster.",
            "Make sure to exercise in high intensity for a short time, rather than in low intensity for a long time. Low-intensity exercise in a long time cannot help your muscles grow. Do exercises following our Coach Tips, and don't skip the rest time.",
            "Walking can lower your body fat percentage and highlight your muscles. It can also increase your muscular endurance, which means you can do more exercise more easily. This is very helpful for your workout. You are recommended to walk over 10,000 steps a day. We've chosen the highest rated (4.7) pedometer app for you.",
            "Make sure you lift more weight than the last time, which is helpful for your muscle growth. Always lifting the same weight won't help you build muscle at all.",
            "Supplements are not good for growing muscle. It may seem very effective, but that's just because the ads are very attractive. The only supplement we recommend is protein powder, which can help you gain protein and speed up muscle growth.",
            "Carbs can propel energy to grow your muscle, it can also help your muscle cells better recover from the damage received during exercise. You can get carbs from potatoes, brown rice, oats, quinoa, sweet potatoes, and pumpkins.",
            "Aesthetically, the V-taper look gives you a bigger overall size. Work your shoulders, chest and back to make them bigger, and you can also do the abs workout to make your waist smaller. We've prepared targeted workout plans for you. Workout on your demand.",
            "Only when you rest can your muscles recover and begin to grow. We recommend you to rest at least 7-8 hours before going on the next workout. This can help you reduce damage to your muscles.",
            "If you do not eat frequently, your body will resist fat loss and instead consume muscle for energy. We recommend you eat 5-6 meals a day to maintain sufficient energy consumption for your body.",
            "Workout promotes the generation and release of endorphins, which helps you relax and enhance your feelings of optimism and satisfaction. By doing workouts regularly, you can have a better state of mind and a better quality of life.",
            "Water helps your body build muscle at an optimal rate. Calculate the amount of water you need daily by the following formula: Weight (pound) X 0.6 = Water Drinking(ounce) / Weight (kg) * 40 = Water Drinking (ml) We recommend this Editors' Choice app for you, it can help you drink enough water every day.",
            "Our workout plan consists of a full-body workout plan and workout plans for targeted areas including chest, abs and shoulder &amp; back. The beginner level of the targeted workout plan can help you lose weight, while the full-body workout plan, intermediate and advanced level of the targeted workout plan mainly help you build muscle.",
            "Eating more fiber can help you grow muscle. Because fiber lessens your biochemical stress, which lowers your muscle growth rate. You can get fiber from whole grains and whole-grain products, fruits, vegetables, seeds, nuts, beans and legumes.",
            "In fact, there is a direct relationship between fat and testosterone levels, so good fat is very important for growing your muscle. If you limit the intake of good fat, you cannot grow muscle. You can get good fats from fish oil, avocado, nuts and olive oil. Your recommended daily fat intake is 0.02% of your body weight.",
            "After 30, you tend to lose muscle mass as you age. The older you are, the faster your muscle mass is lost. However, by exercising, you can slow down the muscle loss speed and maintain the muscles you have now. It's never too late for you to exercise.",
            "Make sure your clothes fit you. Clothes that are too tight will squeeze your body and highlight your body's flaws. On the other hand, when wearing loose clothes, your body contour is not obvious, which won't help you look better either.Fitted clothes can make you look great no matter what shape you are.",
            "Eating a breakfast rich in protein, carbs and good fat can help you adjust your body to build muscle quickly after sleep. It also provides energy for the rest of the day."


        )
        // 7 ki jagah array ka size laga diyo nextInt () me
        val randomNumber= Random().nextInt(21)
        textTip.text=tips[randomNumber]
    }

    override fun onBackPressed() {
if(interstitialAd.isAdLoaded){
        interstitialAd.show()}
        else{
    val i = Intent(this@TipsActivity,MainActivity::class.java)
    startActivity(i)
    finish()
}
    }
    override fun onDestroy() {
        if (adView != null) {
            adView.destroy()
        }
        super.onDestroy()
    }
}
