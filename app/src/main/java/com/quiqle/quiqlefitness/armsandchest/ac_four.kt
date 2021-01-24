package com.quiqle.quiqlefitness.armsandchest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class ac_four : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac_four)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ArmsAndChest::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.acs_4_1,R.drawable.acs_4_2,R.drawable.acs_4_3,R.drawable.acs_4_4,R.drawable.acs_4_5,R.drawable.acs_4_6,R.drawable.acs_4_7)
        val txts = arrayOf("Knee Push Up",
            "Alternate Medicine Ball PushUp",
            "Pike Push Ups",
            "Split Squat Curls",
            "DumbBell Tricep Extension",
            "DumbBell Triceps KickBack",
            "Pullover Leg Raise")
        val desc = arrayOf("Keep your head, hips and torso in line. Maintain your core muscles tight and keep your spine in a neutral position. Inhale as you lower the chest and breathe out as you push back up.",
            "Engage your core muscles and keep your spine neutral, with your head in line with your torso and your torso in line with your hips. Breathe in as you lower your chest and exhale as you push back up.",
            "Start with your arms in line with your spine, straighten your back, engage your core and keep your upper and lower body at a 90 degree angle. Breathe in as you bend your elbows, and lower your body until your head almost touches the mat. Exhale as you straighten your arms and push back to the initial position.",
            "Keep your core tight, your back straight, roll your shoulders back and keep the elbows close to your body. Maintain your feet hip-width apart and breathe in as you flex the knees and curl. Breathe out as you push yourself back up and maintain a smooth and steady rhythm.",
            "While doing the dumbbell triceps extension keep your core tight and your back flat. Breathe in as you lift the dumbbell, and maintain your upper arms stationary and close to your head.",
            "While doing the dumbbell triceps kickback keep your back straight, your core engaged and your head in line with your spine. Exhale as you kick back and maintain the upper arms stationary, only the forearms should move.",
            "Keep your core tight, maintain your wrists and the extended leg straight, and keep a slight bend in your elbows. Breathe out as you pull the dumbbell and your leg up, and maintain the lower back pressed against the floor.")

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
