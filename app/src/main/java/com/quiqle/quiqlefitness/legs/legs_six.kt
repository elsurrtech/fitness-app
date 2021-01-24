package com.quiqle.quiqlefitness.legs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class legs_six : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legs_six)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, Legs::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.l_6_1,R.drawable.l_6_2,R.drawable.l_6_3,R.drawable.l_6_4,R.drawable.l_6_5,R.drawable.l_6_6,R.drawable.l_6_7)
        val txts = arrayOf("Inner Thigh raise to Planks",
            "Heel Beats",
            "Heisman",
            "High Kicks",
            "High Knees",
            "In and OUT Jacks",
            "Inner Thigh Lifts")
        val desc = arrayOf("Keep your upper body stable, engage your core and maintain your top leg straight. Breathe out as you squeeze the inner thigh and lift the hip off the mat. Breathe in as you slowly lower the hip and return to the starting position.",
            "Tighten your core, squeeze the glutes, keep your legs straight and point the toes. Maintain your head, neck, and spine neutral, breathe slowly, and fully engage your glutes and hamstrings to keep the movement slow and controlled.",
            "Tighten your core and bring your knee up as high as you can. Switch quickly between legs and jump on the balls of your feet. Face forward, maintain a smooth and steady breathing pattern and relax your head and neck.",
            "Keep your back straight, your chest open and face forward. Maintain your core engaged and keep the movement smooth and fast. As soon as one foot touches the floor, repeat the move with the opposite leg. Keep your knee straight and exhale as you kick.",
            "Open the chest and keep the knee joints loose. Add support to your back by keeping the core tight and landing slowly on the balls of the feet. Breathe deeply and as naturally as possible, with a smooth and steady rhythm.",
            "Breathe in as you spread your feet and land softly on the toes and balls of the feet. Keep your back straight, your hips back and don’t let the knees extend beyond the toes. Breathe out as you put the pressure on the heels of the feet to jump back up, and reach your hands above your head.",
            "When doing inner thigh lifts keep your upper body stable, your core tight and maintain the working leg straight and the foot flexed. Lift your bottom leg as high as you can, breathe out and squeeze your inner thighs.")

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
        val intent = Intent(this, Legs::class.java)
        startActivity(intent)
        finish()
    }
}
