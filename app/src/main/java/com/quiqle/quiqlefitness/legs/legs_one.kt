package com.quiqle.quiqlefitness.legs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class legs_one : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legs_one)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, Legs::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.l_1_1,R.drawable.l_1_2,R.drawable.l_1_3,R.drawable.l_1_4,R.drawable.l_1_5,R.drawable.l_1_6,R.drawable.l_1_7)
        val txts = arrayOf("180 JUMP Squats",
            "Alternate Dumbel Swings",
            "Alternate Side Lunges",
            "Alternate Side Lunges Touch",
            "Ankle Circles",
            "Ankle Hops",
            "Arm Side Cross Lunges")
        val desc = arrayOf("Keep your breathing pattern smooth and steady, and maintain your back aligned by keeping your chest up and your hips back. Put the pressure on the heels of the feet to jump, and land softly on the toes and balls of the feet with your knees slightly bent. As you squat, don’t let the knees extend beyond the toes and tighten your core as you jump back up.",
            "Roll your shoulders back, open your chest, and keep your spine neutral and aligned. Engage your core and breathe in as you lower your hips back and squat. Exhale as you stand back up, squeeze your glutes and switch the dumbbell to your opposite hand.",
            "Keep your abs tight to give back support, face front and breathe in as you step out to the side with your left leg. Keep your right leg straight, bend your left knee and push your hips back. Breathe out as you use your left foot to push you back into the starting position.",
            "Maintain your balance and give back support by keeping your abs tight. Step out to the side with your right leg and keep your left leg straight. Twist your torso and touch your right foot. Use your right heel to push you back into the starting position and breathe out. Repeat the movement on the left side.",
            "Start with smaller circles and then slowly increase their diameter to open up your ankle joints as much as you can. If you feel any pain or discomfort revert to smaller circles. Breathe slowly and keep the movement smooth and fluid.",
            "Keep your knees soft, breathe slowly and bounce off the floor as fast as you can. Maintain your back straight, face forward and keep a steady pace to get your heart rate up.",
            "Keep your back straight, your abs tight, face front and breathe in as you step out to the side and cross your arms. Breathe out as you use your lead foot to push you back into the starting position and extend your arms out to the sides.")

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
