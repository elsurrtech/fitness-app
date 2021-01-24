package com.quiqle.quiqlefitness.armsandchest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class ac_one : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac_one)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ArmsAndChest::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.acs_1_1,R.drawable.acs_1_2,R.drawable.acs_1_3,R.drawable.acs_1_4,R.drawable.acs_1_5,R.drawable.acs_1_6,R.drawable.acs_1_7)
        val txts = arrayOf("Ankle Tap Push Ups",
            "Around the Worlds",
            "Aysmmetrical Pushups",
            "Band Reverse Plank",
            "Bicep Curls",
            "Chest Fly",
            "Butter Fly Dips")
        val desc = arrayOf("Engage your core and your glutes, maintain a neutral spine, and breathe out as you push yourself back up and touch the ankle. Keep the movement fluid, and inhale as you lower the hips and return to the push up position.",
            "Start with your chest up, shoulders back, arms next to your thighs and keep your elbows slightly bent. Maintain your arms parallel to the floor during the entire movement and inhale as you pull the dumbbells over your head. Breathe out as you return to the starting position",
            "Maintain a neutral spine, engage your core muscles and keep your head in line with your torso, and your torso in line with your hips. Breathe in as you lower your chest, and breathe out as you push back up.",
            "Keep your hands under your shoulders, your arms and legs extended and maintain your head neutral. Roll your shoulders back, open your chest and breathe out as you lift your hips and squeeze the glutes. Inhale as you return to the initial position.",
            "While doing bicep curls keep your knees and elbow joints loose, engage your core muscles and keep your palms facing front. Breathe out as you lift the dumbbells and maintain your back straight, your shoulders back and your head up.",
            "When doing the chest fly exercise, remember to lift your knees so that your back does not come up off the mat. Breathe in as you lower your arms and breathe out as you lift the dumbbells back up. Keep your elbows slightly bent and your arms stationary throughout the entire movement. The movement should only occur at the shoulder joint.",
            "Roll your shoulders back, open the chest, position the hands under your shoulders, place your heels together and let your knees fall open. Breathe out as you press your hips up and extend your arms. Pause at the top to squeeze your legs in, and inhale as you bend your arms and return to the initial position."
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
