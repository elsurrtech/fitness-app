package com.quiqle.quiqlefitness.abs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class abs_four : AppCompatActivity() {

    private lateinit var saveData: SaveData


    override fun onCreate(savedInstanceState: Bundle?) {


        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_four)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, AbsActivity::class.java)
            startActivity(intent)
        }

        //initialise arrays for inflater
        val gifs = arrayOf(R.drawable.abs_4_1,R.drawable.abs_4_2,R.drawable.abs_4_3,R.drawable.abs_4_4,R.drawable.abs_4_5,R.drawable.abs_4_6,R.drawable.abs_4_7)
        val txts = arrayOf("Dead Bug",
            "Donkey Kick twist",
            "Double Leg Stretch",
            "Downward Dog Crunch",
            "Dumbbell Leg Loop",
            "Dumbbell Side Bend",
            "Flutter Kicks")
        val desc = arrayOf( "When doing the dead bug exercise keep your lower back flat against the floor, to avoid placing too much stress on it, and keep your abdominal muscles tight. Breathe out as you lower your leg and extend your arm, and hold them parallel to the floor. Breathe in as you return to the starting position and then switch sides.",
            "Keep your head, neck and spine neutral, relax your shoulders and engage your core. Breathe in as you extend your leg, and twist your torso only from the ribs up. Exhale as you bring the knee toward the elbow and crunch.",
            "Keep your lower back pressed against the floor, engage the core and back muscles, relax your head and neck and keep your shoulders away from the ears. Inhale deeply as you extend your arms and legs, and exhale as you hug the chins toward the chest.",
            "Keep your spine, head, and neck neutral and aligned, engage your core and glutes, and maintain your wrists and elbows straight. Breathe out as you bend the knee and bring it toward the elbow and inhale as you extend the leg up and back, being careful not to arch your back.",
            "Keep your upper body stable and use your core strength to maintain a neutral spine at all times. Relax your head and neck, keep your legs off the floor, and breathe out as you drive your knee toward the chest and squeeze the abs.",
            "Keep your head and neck in a neutral position, engage your core and inhale as you bend to the side. Face front and don’t lean forward nor back, bend slowly and smoothly to the side and go only as far as it feels comfortable. Pause for a second and then breathe out as you return to the starting position.",
            "Maintain your abs and core engaged at all times and keep your lower back pressed against the floor. Breathe slowly and keep your chin off your chest, your head in a neutral position and your legs straight."

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
