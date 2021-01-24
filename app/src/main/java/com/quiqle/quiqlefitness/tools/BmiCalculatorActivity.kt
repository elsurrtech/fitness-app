package com.quiqle.quiqlefitness.tools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.quiqle.quiqlefitness.R
import com.quiqle.quiqlefitness.SaveData
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import com.facebook.ads.AudienceNetworkAds
import kotlinx.android.synthetic.main.activity_bmi_calculator.*

class BmiCalculatorActivity : AppCompatActivity() {

    private lateinit var saveData: SaveData
    private  lateinit  var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)
        AudienceNetworkAds.initialize(this);
        adView = AdView(this, "612186619649274_612575519610384", AdSize.BANNER_HEIGHT_50)
        val adscontainer = findViewById<LinearLayout>(R.id.banner_container)
        adscontainer.addView(adView)
        adView.loadAd()
        //Button
        val btn = findViewById<ImageView>(R.id.btnBack)
        btn.setOnClickListener {
            val intent = Intent(this, ToolsActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnCalculateBMI.setOnClickListener {

                if (etWeight.text.toString() == ""){
                    Toast.makeText(this,"Enter Weight",Toast.LENGTH_SHORT).show()
                }else if (etHeight.text.toString() == ""){
                    Toast.makeText(this,"Enter Height",Toast.LENGTH_SHORT).show()
                }
                else{
                    bmi()
                }

        }

    }

    private fun bmi(){
        val weight =  findViewById<EditText>(R.id.etWeight)
        val w = weight.text.toString().toFloat()
        val height = findViewById<EditText>(R.id.etHeight)
        val h = height.text.toString().toFloat()
        val x: Float = w/((h/100)*(h/100))

        text_bmi.visibility = View.VISIBLE
        text_status.visibility = View.VISIBLE

        text_bmi.text = "BMI: "+ x.toString()

        if (x<15){
            text_status.text = "Status: Very Slightly Underweight"
        }else if (x>=15 && x<16){
            text_status.text = "Status: Slightly Underweight"
        }else if (x>=16 && x<18.5){
            text_status.text = "Status: Underweight"
        }else if (x in 18.5..25.0){
            text_status.text = "Status: Normal"
        }else if (x>25 && x<=30){
            text_status.text = "Status: Overweight"
        }else{
            text_status.text = "Status: Obese"
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this,ToolsActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onDestroy() {
        if (adView != null) {
            adView.destroy()
        }
        super.onDestroy()
    }
}
