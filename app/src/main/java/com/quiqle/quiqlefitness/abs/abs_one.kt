package com.quiqle.quiqlefitness.abs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class abs_one : AppCompatActivity() {
    private lateinit var saveData: SaveData


    override fun onCreate(savedInstanceState: Bundle?) {


        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_one)

        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this@abs_one, AbsActivity::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.abs_1_1,R.drawable.abs_1_2,R.drawable.abs_1_3,R.drawable.abs_1_4,R.drawable.abs_1_5,R.drawable.abs_1_6,R.drawable.abs_1_7)
        val txts = arrayOf("Abdominal Bridge",
                            "Ankle Tap Push Ups",
                            "Balance Chop",
                            "Band Leg Abduction Crunch",
                            "Band Reverse Plank",
                            "Basketball Shots",
                            "Bent Leg Jack Knife")
        val desc = arrayOf( "Keep the elbows directly below the shoulders, maintain your shoulders back and keep the knees on the mat. As you exhale, tighten the core and lift your hips slowly until they’re almost at shoulder-height. Pause for 2 seconds and then inhale as you slowly return to the starting position.",
                            "Engage your core and your glutes, maintain a neutral spine, and breathe out as you push yourself back up and touch the ankle. Keep the movement fluid, and inhale as you lower the hips and return to the push up position.",
                            "Tighten your core, keep your feet and knees pointing in the same direction, and breathe in as you raise the dumbbell above your head. Exhale as you lift your knee and bring the dumbbell close to your hip.",
                            "Engage your core, keep your neck, head and back neutral and exhale as you crunch. Spread your legs wide enough that you feel tension across the band and then inhale as you slowly lower your upper body back to the mat and release tension.",
                            "Keep your hands under your shoulders, your arms and legs extended and maintain your head neutral. Roll your shoulders back, open your chest and breathe out as you lift your hips and squeeze the glutes. Inhale as you return to the initial position.",
                            "Tighten your core, press your hips back, keep your chest up and maintain your back aligned. Don’t let the knees extend beyond the toes and breathe out as you push through the heels to jump up. Extend your arms above your head and slightly to the left, and gaze up. Land softly on the balls of your feet, with your knees slightly bent.",
                            "Use your core strength to keep your upper body stable, and maintain a neutral spine during the entire exercise. Breathe out as you bring your knees toward the chest and squeeze in the abs. Breathe in as you return to the starting position without ever letting your hands and feet touch the floor."
                            )

        //start button
        val btnStart = findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener {
            val intent = Intent(this,StartActivity::class.java)
            val bundle = Bundle()
            bundle.putIntArray("gifs", gifs.toIntArray())
            bundle.putStringArray("name",txts)
            bundle.putStringArray("desc",desc)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        val listView = findViewById<ListView>(R.id.listView)
        val custom_list_data = ArrayList<CList>()
        val custom_list = CListAdaptor(this,custom_list_data)

        custom_list_data.add(CList(gifs[0],txts[0],"x5"))
        custom_list_data.add(CList(gifs[1],txts[1],"x5"))
        custom_list_data.add(CList(gifs[2],txts[2],"x5"))
        custom_list_data.add(CList(gifs[3],txts[3],"x5"))
        custom_list_data.add(CList(gifs[4],txts[4],"x5"))
        custom_list_data.add(CList(gifs[5],txts[5],"x5"))
        custom_list_data.add(CList(gifs[6],txts[6],"x5"))

        listView.adapter = custom_list
        listView.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
            showAlert(view,gifs[position],txts[position],desc[position])

        }
    }

    //Dialog Function
    fun showAlert(v: View, img:Int, txt:String, dsc:String){
        val inflater = layoutInflater
        val inflate_view = inflater.inflate(R.layout.custom_alert_dialog,null)

        val image = inflate_view!!.findViewById<ImageView>(R.id.i1)
        val text = inflate_view.findViewById<TextView>(R.id.t1)
        val desc = inflate_view.findViewById<TextView>(R.id.desc)

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setView(inflate_view)
        alertDialog.setCancelable(false)

        alertDialog.setNegativeButton("Cancel"){dialog, which ->
        }

        val dialog = alertDialog.create()
        dialog.show()

        image.setImageResource(img)
        text.setText(txt)
        desc.setText(dsc)

    }
    override fun onBackPressed() {
        val intent = Intent(this, AbsActivity::class.java)
        startActivity(intent)
        finish()
    }

}
