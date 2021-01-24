package com.quiqle.quiqlefitness.armsandchest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class ac_three : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac_three)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ArmsAndChest::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.acs_3_1,R.drawable.acs_3_2,R.drawable.acs_3_3,R.drawable.acs_3_4,R.drawable.acs_3_5,R.drawable.acs_3_6,R.drawable.acs_3_7)
        val txts = arrayOf("Elbow Squeeze Shoulder Press",
            "Hindu Push Ups",
            "Side Lunge Curl",
            "Bicep Curls",
            "Single Leg Tricep Dips",
            "Lying Tricep Extension",
            "Knee and Elbow Press Up")
        val desc = arrayOf("Keep your back straight, engage the core, open the chest and face front. Breathe out as you bring the elbows and forearms toward the midline of the body, and squeeze the chest. Keep your arms at a 90-degree angle and breathe in as you return to the starting position. Exhale as you push the dumbbells up and over your head, and maintain your hands shoulder-width apart.",
            "Engage your core, relax the spine and neck, open the shoulders and keep the movement fluid and smooth. Breathe in deeply as you slowly move from downward dog to upward dog pose, keeping the elbows close to your body. Exhale as you lift the hips and return to downward dog pose.",
            "Keep your core tight, your back straight and maintain your palms facing front. Don’t step too wide to the side, breathe in as you lunge and keep the knees pointing in the same direction as the feet. Breathe out as you return to the starting position, lift the dumbbells and curl.",
            "While doing bicep curls keep your knees and elbow joints loose, engage your core muscles and keep your palms facing front. Breathe out as you lift the dumbbells and maintain your back straight, your shoulders back and your head up.",
            "When doing single leg tricep dips roll your shoulders back, open the chest, place your hands under your shoulders and maintain the leg that’s on the mat perpendicular to the floor. Inhale as you bend your arms, keep the elbows pointing back and don’t bend at the hips. Breathe out as you extend your arms and lift yourself back up.",
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
