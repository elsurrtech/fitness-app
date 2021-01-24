package com.quiqle.quiqlefitness.abs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class abs_two : AppCompatActivity() {

    private lateinit var saveData: SaveData


    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_two)

        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, AbsActivity::class.java)
            startActivity(intent)
        }

        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.abs_2_1,R.drawable.abs_2_2,R.drawable.abs_2_3,R.drawable.abs_2_4,R.drawable.abs_2_5,R.drawable.abs_2_6,R.drawable.abs_2_7)
        val txts = arrayOf("Bent Over Twist",
            "BiCycle Crunches",
            "Bird Dogs",
            "Boat Twist",
            "BreakDancer Kick",
            "Bridge and Twist",
            "Burpees")
        val desc = arrayOf( "Keep your back and your arms straight and engage your abs. Maintain a neutral spine and use your abs and obliques to control the movement. Breathe deeply and inhale as you lift your torso and return to the starting position.",
            "When doing bicycle crunches, engage your core muscles, open the elbows and keep your neck relaxed. Breathe out as you crunch, keep your shoulder blades off the mat and maintain a steady rhythm throughout the entire exercise.",
            "When doing bird dogs keep your spine in a neutral position, with your head and neck in alignment, and relax your shoulders. Breathe in as you lift your arm and the opposite leg, and maintain your torso stable by engaging your core.",
            "Twist your torso only from the ribs up, tighten your core and make sure your back is straight at all times. Keep your feet off the floor and, if you can, bring your legs up until they’re parallel with the floor. Breathe out as you twist your torso.",
            "Start on all fours, with your wrists underneath your shoulders and your knees underneath your hips. Lift your knees off the floor, engage your core, and keep your back, neck, and head neutral. Exhale as you rotate your hips to the left and kick your right leg under your body.",
            "Roll your shoulders back, relax your head and neck and engage your core. Breathe out, lift your butt as high as you can and squeeze the glutes. Twist your torso, reach your arm up toward the ceiling and then breathe in as you lower the hips and return to the starting position.",
            "If you want to include burpees in your workout routine you need to focus on maintaining perfect form. Keep your back straight and your core engaged at all times and maintain a natural and regular breathing pattern throughout the exercise. If done incorrectly, burpees can put your body at high risk of injury."
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
