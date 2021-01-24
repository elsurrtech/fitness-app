package com.quiqle.quiqlefitness.abs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class abs_six : AppCompatActivity() {
    private lateinit var saveData: SaveData


    override fun onCreate(savedInstanceState: Bundle?) {


        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_six)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, AbsActivity::class.java)
            startActivity(intent)
        }

        //initialise arrays for inflater
        val gifs = arrayOf(R.drawable.abs_6_1,R.drawable.abs_6_2,R.drawable.abs_6_3,R.drawable.abs_6_4,R.drawable.abs_6_5,R.drawable.abs_6_6,R.drawable.abs_6_7)
        val txts = arrayOf("Knee Hugs",
            "Knee to Elbow Kickback",
            "Leaning Camel",
            "Lunge Twist",
            "Lying Side Crunch",
            "Mountain Climbers",
            "Mountain Climber Twist")
        val desc = arrayOf( "Keep your upper body stable, use your core strength to stabilize your body, and maintain a neutral spine during the entire exercise. Exhale as you bring your knees toward the chest and squeeze your abs.",
            "Keep your spine, head and neck neutral and aligned, and engage your core and glutes. Breathe out as you bring the knee toward the elbow and crunch. Inhale as you extend and kick your leg up, being careful not to arch your back.",
            "Relax your shoulders, open your chest and engage your core, glutes and quads. Keep your back straight and your torso stable at all times, and breathe out as you lean your torso back. Inhale as you return to the starting position.",
            "As you lunge keep the weight in the front heel, squeeze your glutes and keep your core tight. Make sure that both legs form a 90-degree angle and that your hips are in alignment. Twist your torso to the same side as your front leg and only from the ribs up.",
            "Engage your core, keep your back, head, and neck neutral and face front. Exhale as you squeeze the obliques and crunch. Breathe in as you return to the starting position.",
            "Maintain a plank position during the entire exercise, keep your core engaged, your hips low and your body in a straight line. It’s important to maintain proper alignment to get the most out of the exercise. Don’t sacrifice the form for the speed and try to breathe as slowly as possible.",
            "Keep your core engaged, your hips low and maintain your head, neck, and back aligned. Breathe slowly and keep the movement steady and controlled."
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
