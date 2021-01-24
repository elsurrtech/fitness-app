package com.quiqle.quiqlefitness.armsandchest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class ac_five : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac_five)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ArmsAndChest::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.acs_5_1,R.drawable.acs_5_2,R.drawable.acs_5_3,R.drawable.acs_5_4,R.drawable.acs_5_5,R.drawable.acs_5_6,R.drawable.acs_5_7)
        val txts = arrayOf("Push Ups",
            "SpiderMan PushUps",
            "Stability Ball Chest Press",
            "BiceUp Curls",
            "Table Top Reverse Pike ",
            "Triceps Dips",
            "Squat Tricep Extension")
        val desc = arrayOf("While doing the push-up exercise focus on keeping your head in line with your torso, and your torso in line with your hips. Engage your core muscles, maintain your body in a straight line and exhale as you push back. Take the pressure off the wrists and place it on the outside of your hands.",
            "Before you can do spiderman push ups you need to be able to do a regular push up. Remember to keep your spine neutral, engage your core muscles and exhale as you push yourself back up. Keep your knees high and away from the floor and make sure that your movement is fluid and well coordinated. By the time you get to the bottom of your push up your knee needs to be up.",
            "Keep your head and spine in a neutral position, raise the hips, tighten your core and breathe out as you squeeze your chest and push the dumbbells up. Don’t lock your elbows and inhale as you lower the dumbbells until your arms are slightly below parallel to the floor.",
            "While doing bicep curls keep your knees and elbow joints loose, engage your core muscles and keep your palms facing front. Breathe out as you lift the dumbbells and maintain your back straight, your shoulders back and your head up.",
            "Lift your hips off the floor and get into a tabletop position, with your torso parallel to the floor, your hands directly under your shoulders, your arms straight, and your ankles under your knees. Roll your shoulders back, open your chest and keep your head neutral. Breathe out as you lower your hips and straighten your legs, keeping your arms straight, your spine long and your hips off the floor. Inhale as you lift your hips and return to the tabletop position.",
            "When doing tricep dips roll your shoulders back, open the chest, keep your neck nice and tall and place your hands underneath your shoulders. Inhale as you bend your elbows and breathe out as you extend the elbows and lift yourself back up.",
            "Open your chest, engage your core and maintain your back flat, your hips back and your elbows pointing forward. Inhale as you squat and lower the dumbbells to the floor. Breathe out as you put pressure on the heels to stand up and press your arms up.")

        //start button
        val btnStart = findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
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
        val intent = Intent(this, ArmsAndChest::class.java)
        startActivity(intent)
        finish()
    }

}
