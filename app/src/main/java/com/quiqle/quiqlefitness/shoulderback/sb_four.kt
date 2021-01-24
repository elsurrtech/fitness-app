package com.quiqle.quiqlefitness.shoulderback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class sb_four : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sb_four)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ShoulderBack::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.sb_4_1,R.drawable.sb_4_2,R.drawable.sb_4_3,R.drawable.sb_4_4,R.drawable.sb_4_5,R.drawable.sb_4_6,R.drawable.sb_4_7)
        val txts = arrayOf("Curtsy Lunge Kick Raise",
            "Double Legs Donkey Kicks",
            "DumbBell Front Raise",
            "DumbBell Lateral Raise",
            "Cobra Lat PullDown",
            "Crab Kicks",
            "Crab Toe Touches")
        val desc = arrayOf("Engage your core, face forward, open your chest and keep your back straight. As you lunge, keep your front knee over your ankle, and keep your toes pointing in the same direction as your knees. Exhale as you stand up and, as you kick out to the side and raise the dumbbell, maintain your back aligned.",
            "Engage your core and glutes, maintain your back straight at all times and keep your head, spine, and neck in a neutral position. Keep your wrists and elbows straight and breathe out as you kick your legs up. Inhale as you land softly on the toes and balls of the feet and with your knees slightly bent.",
            "Keep the elbows slightly bent, engage your core and maintain your back straight. Breathe out as you lift the dumbbells, keep the movement slow and smooth and breathe in as you lower the dumbbells back to the starting position.",
            "While doing the dumbbell lateral raise exercise keep your core engaged, your back straight and face front. Inhale as you lower the dumbbells and maintain your elbows and your knees slightly bent.",
            "When doing the cobra lat pulldown keep your head in line with your spine and keep your abs tight. Breathe out as you lift your torso and, as you bend your arms, pull your shoulders away from your ears and squeeze your shoulder blades.",
            "Keep your core engaged, your hips as high as possible and switch legs as quickly as you can without losing form or using momentum. Maintain a steady breathing pattern, relax your neck and stay in control.",
            "Keep your core engaged, your back neutral, relax your neck and lift your hips as high as possible. Switch legs quickly, without losing form or using momentum, and maintain a steady breathing pattern.")

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
        val intent = Intent(this, ShoulderBack::class.java)
        startActivity(intent)
        finish()
    }
}
