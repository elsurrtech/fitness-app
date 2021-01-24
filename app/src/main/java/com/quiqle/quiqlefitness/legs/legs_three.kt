package com.quiqle.quiqlefitness.legs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class legs_three : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legs_three)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, Legs::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.l_3_1,R.drawable.l_3_2,R.drawable.l_3_3,R.drawable.l_3_4,R.drawable.l_3_5,R.drawable.l_3_6,R.drawable.l_3_7)
        val txts = arrayOf("Circles In Sky",
            "Core Control Rear Lunges",
            "Cossack Squat",
            "Cross Jacks",
            "Curtsy Lunges",
            "Curtsy Lunges Kick Raise",
            "Curtsy Lunges Side Kick")
        val desc = arrayOf("Engage your core, look up and keep your head and neck relaxed. Exhale as you crunch and lift your shoulders and legs off the floor. Breathe smoothly as you rotate your leg and keep the thigh perpendicular to the floor.",
            "Engage your core for stability, face front, open your chest and keep your back straight. As you lunge, keep the front knee aligned with the ankle and keep your weight loaded in the front heel. Exhale as you stand up and maintain a smooth and steady rhythm.",
            "Tighten your core, keep your back straight at all times, and maintain your knees in line with your toes. Breathe in as you squat, and keep both heels on the floor. Exhale as you push through the heels to return to the starting position.",
            "Tighten your core, keep your knees and elbows loose, and maintain a smooth and steady breathing pattern. Land softly on the balls of your feet with your knees slightly bent, and keep your feet, hips, and knees in line.",
            "When doing the curtsy lunge, engage your core muscles, face forward, maintain your upper body nice and tall and keep your back straight. Breathe in as you lunge and keep your front knee over your ankle. Bend both knees, until your calves and thighs form a 90-degree angle, and keep the toes pointing in the same direction as the knees.",
            "Engage your core, face forward, open your chest and keep your back straight. As you lunge, keep your front knee over your ankle, and keep your toes pointing in the same direction as your knees. Exhale as you stand up and, as you kick out to the side and raise the dumbbell, maintain your back aligned.",
            "Engage your core, open the chest, face front and keep your back straight. As you lunge, keep the front knee over the ankle, and keep the toes pointing in the same direction as the knees. Exhale as you stand back up and kick out to the side, and maintain your core tight.")

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
        val intent = Intent(this, Legs::class.java)
        startActivity(intent)
        finish()
    }
}
