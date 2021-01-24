package com.quiqle.quiqlefitness.abs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class abs_three : AppCompatActivity() {
    private lateinit var saveData: SaveData


    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_three)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, AbsActivity::class.java)
            startActivity(intent)
        }

        //initialise arrays for inflater
        val gifs = arrayOf(R.drawable.abs_3_1,R.drawable.abs_3_2,R.drawable.abs_3_3,R.drawable.abs_3_4,R.drawable.abs_3_5,R.drawable.abs_3_6,R.drawable.abs_3_7)
        val txts = arrayOf("Chest Press with legs extended",
            "Core Control Rear Lunge",
            "Crab Kicks",
            "Crab Toe Touches",
            "Cross Crunches",
            "Crunch Chop",
            "Crunches")
        val desc = arrayOf( "Engage your core and keep your head, back and shoulders pressed against the floor. Breathe out as you squeeze your chest and push the dumbbells up, being careful not to fully extend your elbows. Inhale as you lower the dumbbells and return to the initial position.",
            "Engage your core for stability, face front, open your chest and keep your back straight. As you lunge, keep the front knee aligned with the ankle and keep your weight loaded in the front heel. Exhale as you stand up and maintain a smooth and steady rhythm.",
            "Keep your core engaged, your hips as high as possible and switch legs as quickly as you can without losing form or using momentum. Maintain a steady breathing pattern, relax your neck and stay in control.",
            "Keep your core engaged, your back neutral, relax your neck and lift your hips as high as possible. Switch legs quickly, without losing form or using momentum, and maintain a steady breathing pattern.",
            "When doing cross crunches make sure that your abs are doing all the work. Keep your spine in a neutral position and support your neck with your hand. Breathe out as you squeeze your abs and keep your legs static as you bring your elbow toward the opposite knee.",
            "Keep your core engaged and breathe out as you crunch and chop your hands through your legs. Maintain your back, head, and neck neutral, and inhale as you close your legs and slowly lower your upper body back to the floor.",
            "When doing crunches keep your head in a neutral position, with your eyes on the ceiling and the chin off your chest. Breathe out as you crunch, maintain your core muscles tight and engaged and keep the elbows out."

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
    override fun onBackPressed() {
        val intent = Intent(this, AbsActivity::class.java)
        startActivity(intent)
        finish()
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
}
