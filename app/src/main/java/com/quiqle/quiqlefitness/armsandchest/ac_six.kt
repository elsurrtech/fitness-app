package com.quiqle.quiqlefitness.armsandchest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class ac_six : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac_six)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ArmsAndChest::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.acs_6_1,R.drawable.acs_6_2,R.drawable.acs_6_3,R.drawable.acs_6_4,R.drawable.acs_6_5,R.drawable.acs_6_6,R.drawable.acs_6_7)
        val txts = arrayOf("Staggered Arm Knee PushUp",
            "Staggered Arm PushUp",
            "Standing Chest Fly",
            "Up Down Plank",
            "V Sit Curl Press",
            "Lying Tricep Extension",
            "Knee and Elbow Press Up")
        val desc = arrayOf("Engage your core, maintain your spine neutral and keep your head, hips and torso in line. Inhale as you lower the chest, pause for 1 or 2 seconds and exhale as you extend your arms and push back to the starting position.",
            "Keep your body straight, with your head in line with your torso and your torso in line with your hips, and engage your core. Inhale as you lower your chest and then pause for 1 to 2 seconds. Exhale as you extend your arms and push back up.",
            "Breathe out as you bring your elbows and forearms toward the midline of the body and squeeze the chest. Relax your chest, maintain your upper arms parallel to the floor and keep your arms at a 90-degree angle. The movement should only happen at the shoulder joint.",
            "Engage your glutes, tighten your core, and keep your head, neck and spine aligned. Inhale as you bend your arms and place your elbows on the mat, and breathe out as you straighten your arms and place your hands on the mat.",
            "Keep your back straight, engage your core and maintain your head and neck neutral at all times. Breathe out as push the dumbbells up and fully extend your arms and breathe in as you return to the initial position.",
            "When doing the lying triceps extension, be sure to keep your upper arms stationary, only your elbow joints should move. Breathe out as you bring the dumbbells up and keep your elbows close to your body during the entire movement.",
            "Keep your back, head, and neck in line and neutral. Exhale as you straighten your legs and arms and lift the hips. Breathe in as you return to the starting position and keep the movement in your knee and elbow joints.")



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
