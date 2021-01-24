package com.quiqle.quiqlefitness.abs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class abs_five : AppCompatActivity() {

    private lateinit var saveData: SaveData


    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_five)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, AbsActivity::class.java)
            startActivity(intent)
        }

        //initialise arrays for inflater
        val gifs = arrayOf(R.drawable.abs_5_1,R.drawable.abs_5_2,R.drawable.abs_5_3,R.drawable.abs_5_4,R.drawable.abs_5_5,R.drawable.abs_5_6,R.drawable.abs_5_7)
        val txts = arrayOf("Frog Crunches",
            "Glute Bridge Reach",
            "Heel Touchers",
            "InchWorm",
            "Inner Thigh Squeeze and Lift",
            "Inverted V Plank",
            "Kick Crunch")
        val desc = arrayOf( "Keep your head in a neutral position, with the chin off your chest and your eyes on the ceiling. Keep your lower back pressed into the mat throughout the exercise, exhale as you crunch, and maintain your core tight and engaged.",
            "Engage your core and glutes, and keep your knees in line with your hips and feet. Breathe out as you squeeze the glutes and lift your hips off the mat, and reach one arm up and across your body. Inhale as you return to the initial position.",
            "When doing alternate heel touchers, keep your eyes on the ceiling, your chin off your chest and maintain your head in a neutral position. Breathe out as you crunch and push your belly button into the spine.",
            "Keep your legs straight, don’t let your hips sag and maintain a neutral spine. Engage your core muscles to stabilize the movement and walk your hands out as far as you can without losing form. Breathe deeply and lift the heels as you walk out, keeping the toes in the same spot.",
            "Tighten the core, keep your back relaxed and breathe out as you squeeze the inner thighs and lift the ball. Engage your hips and glutes and breathe in as you lower the ball back to the starting position.",
            "Keep your abs in tight, engage your glutes and maintain your back neutral. Breathe out as you press through your shoulders and arms to lift the hips toward the ceiling. Inhale as you lower the hips and return to plank position.",
            "Look straight ahead, keep your back straight and open your chest. Engage your core and breathe out as you crunch and touch your foot with the opposite hand. Keep the movement smooth and fast and, as soon as one foot touches the floor, repeat the move on the opposite side."
        )

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
            showAlert(view,gifs[position] as Int,txts[position],desc[position])

        }
    }
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
