package com.quiqle.quiqlefitness.armsandchest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class ac_two : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac_two)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ArmsAndChest::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.acs_2_1,R.drawable.acs_2_2,R.drawable.acs_2_3,R.drawable.acs_2_4,R.drawable.acs_2_5,R.drawable.acs_2_6,R.drawable.acs_2_7)
        val txts = arrayOf("One Arm Tricep Push up",
            "One Arm Tricep kickback",
            "DumbBell Chest Press",
            "Decline Push Up",
            "Concentration Curls",
            "Bicep Stretch",
            "DumbBell Pullover ")
        val desc = arrayOf("Keep your shoulders, hips and feet stacked and engage your core. Breathe out as you slowly straighten your arm and push your torso up, and inhale as you bend your arm and lower your torso back to the starting position.",
            "Keep your core tight, your back straight and keep your head in line with your spine and the back leg. Breathe out as you kick back and maintain the upper arm stationary, only the forearm should move.",
            "When doing the dumbbell chest press exercise, keep your spine in a neutral position and breathe out as you push the dumbbells up, being careful not to lock your elbows.",
            "Keep your body in a straight line, with your head in line with your torso and your torso in line with your hips. Engage your core and breathe in as you slowly bend your elbows and lower your chest to the floor. Exhale as you straighten your arms and push back to the starting position.",
            "Keep your chest up, face front, relax your neck and back, and breathe out as you curl and bring the dumbbell toward the chest. Inhale as you lower the dumbbell back to the starting position and be careful not to hyperextend your elbow.",
            "Breathe normally and slightly deeper without holding your breath. Raise your arms until you feel a gentle stretch in your biceps and exhale as you deepen the stretch. To prevent injuries, keep your back straight and don’t bounce your body while stretching.",
            "When doing the dumbbell pullover keep your elbows slightly bent and don’t move them during the entire exercise. Keep your core tight, your wrists straight and breathe out as you pull the dumbbells up and over your chest")

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
