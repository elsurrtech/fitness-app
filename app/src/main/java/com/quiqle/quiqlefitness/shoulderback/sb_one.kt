package com.quiqle.quiqlefitness.shoulderback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class sb_one : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sb_one)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ShoulderBack::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.sb_1_1,R.drawable.sb_1_2,R.drawable.sb_1_3,R.drawable.sb_1_4,R.drawable.sb_1_5,R.drawable.sb_1_6,R.drawable.sb_1_7)
        val txts = arrayOf("Arm Circles",
            "Arms Cross Side Lunge",
            "Arm Swings",
            "Arnold Shoulder Press",
            "Alternating Superman",
            "Back Extensions",
            "Back Stretch")
        val desc = arrayOf("While doing arm circles engage your core muscles, keep your back and your arms straight and maintain a smooth and deep breathing pattern. Keep your head up and your body in a T formation during the entire exercise.",
            "Keep your back straight, your abs tight, face front and breathe in as you step out to the side and cross your arms. Breathe out as you use your lead foot to push you back into the starting position and extend your arms out to the sides.",
            "Keep your abs in tight and your back straight. Face forward, breathe slowly and use your muscles to propel the movement. Your arms should swing in a steady and fluid motion, as close as possible to their full range of motion.",
            "Face forward, keep your back straight, open your chest, and exhale as you push the dumbbells up and rotate your palms. Give back support by engaging your core, maintain your hands shoulder-width apart and keep your elbows under your wrists.",
            "When doing the alternating superman, keep your arms and legs fully extended and maintain a neutral spine. Breathe in as you lift one arm and leg, pull in your abs and keep your core muscles tight.",
            "Use your hands to support your head and remove all the pressure from your neck. Engage your core muscles, keep your chin up and breathe out as you lift your torso up. Keep the movement smooth and within a comfortable range of motion.",
            "Keep your spine relaxed, breathe in deeply, drop your head and round your back and neck toward the ceiling. As you exhale, lift your head and start to round your belly toward the floor. Do this movement slowly and don’t bounce between positions.")

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
        val intent = Intent(this, ShoulderBack::class.java)
        startActivity(intent)
        finish()
    }
}
