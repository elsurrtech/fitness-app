package com.quiqle.quiqlefitness.shoulderback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class sb_two : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sb_two)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ShoulderBack::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.sb_2_1,R.drawable.sb_2_2,R.drawable.sb_2_3,R.drawable.sb_2_4,R.drawable.sb_2_5,R.drawable.sb_2_6,R.drawable.sb_2_7)
        val txts = arrayOf("Bear Walk",
            "Bent Over Lateral Raise",
            "Bent Over Row",
            "Bent Over Row Press",
            "Balance Chop",
            "Bear Walk",
            "Bent over Row")
        val desc = arrayOf("Keep your knees slightly bent, your back flat and your arms straight. Engage your core, lift your hips, and exhale as you reach your hand and the opposite foot forward.",
            "While doing the bent over lateral raise keep your knees and elbow joints loose and slightly bent, and keep your head in line with your back. Breathe out as you lift the dumbbells and maintain your torso stationary and your core engaged.",
            "While doing the dumbbell bent over row, pull the shoulders back, keep your head up and facing forward and maintain your back straight. Exhale as you pull the dumbbells toward the waist and keep the elbows close to your body during the entire movement.",
            "Keep your shoulders back, maintain your back straight, tighten your core and keep your head up. Inhale as you pull the dumbbell toward the waistline and keep the elbow close to your body. Rotate your torso to the right, face up and exhale as you push the dumbbell up and over your head.",
            "Tighten your core, keep your feet and knees pointing in the same direction, and breathe in as you raise the dumbbell above your head. Exhale as you lift your knee and bring the dumbbell close to your hip.",
            "Keep your knees slightly bent, your back flat and your arms straight. Engage your core, lift your hips, and exhale as you reach your hand and the opposite foot forward.",
            "While doing the dumbbell bent over row, pull the shoulders back, keep your head up and facing forward and maintain your back straight. Exhale as you pull the dumbbells toward the waist and keep the elbows close to your body during the entire movement.")

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

