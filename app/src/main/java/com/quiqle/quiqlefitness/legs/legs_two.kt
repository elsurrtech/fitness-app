package com.quiqle.quiqlefitness.legs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class legs_two : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legs_two)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, Legs::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.l_2_1,R.drawable.l_2_2,R.drawable.l_2_3,R.drawable.l_2_4,R.drawable.l_2_5,R.drawable.l_2_6,R.drawable.l_2_7)
        val txts = arrayOf("Bear Squat",
            "Boxer Squat Punch",
            "Bulgarian Split Squat",
            "Burpees",
            "Calf Raise",
            "Alternate Side Lunges Touch",
            "Ankle Circles")
        val desc = arrayOf("Keep your back, head, and neck in a neutral position and maintain your upper body stable. Breathe out as you straighten your legs and lift the hips, and keep the movement in your knee joints. Inhale as you return to the starting position.",
            "Keep your hips back, your chest up and don’t let your knees extend beyond your toes. Stand up, shift your weight to the right leg, rotate your torso to the right side and punch with your left arm. Inhale as you squat and repeat the movement on the left side.",
            "When doing the bulgarian split squat, keep your back straight, your chest open and face front. Maintain your balance by engaging your core and keep your weight in the front heel. Breathe in as you lunge and don’t let your knee go past your toes.",
            "If you want to include burpees in your workout routine you need to focus on maintaining perfect form. Keep your back straight and your core engaged at all times and maintain a natural and regular breathing pattern throughout the exercise. If done incorrectly, burpees can put your body at high risk of injury.",
            "When doing calf raises, brace your core, face forward and keep your toes pointing straight ahead. Raise your heels and breathe out as you squeeze your calves. Breathe in as you lower your heels and slowly return to the starting position",
            "Maintain your balance and give back support by keeping your abs tight. Step out to the side with your right leg and keep your left leg straight. Twist your torso and touch your right foot. Use your right heel to push you back into the starting position and breathe out. Repeat the movement on the left side.",
            "Start with smaller circles and then slowly increase their diameter to open up your ankle joints as much as you can. If you feel any pain or discomfort revert to smaller circles. Breathe slowly and keep the movement smooth and fluid.")

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
