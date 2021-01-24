package com.quiqle.quiqlefitness.legs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class legs_four : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legs_four)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, Legs::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.l_4_1,R.drawable.l_4_2,R.drawable.l_4_3,R.drawable.l_4_4,R.drawable.l_4_5,R.drawable.l_4_6,R.drawable.l_4_7)
        val txts = arrayOf("Diamond Kicks",
            "Double Pulse Squat Jumps",
            "DumbBell Swing",
            "DumbBell Thrusters",
            "Figure 8 Squat",
            "Fingertip to Toe Jacks",
            "Flutter Kick Squats")
        val desc = arrayOf("Engage your core, keep your back flat, look up and maintain your head and neck neutral. Start with your legs in a frog position, extend them out to the sides, and engage the inner thighs. Breathe out as you bring both legs together, and then inhale as you bend the knees and return to the frog position.",
            "Keep your chest up, your hips back and don’t let the knees extend beyond the toes. Inhale as you squat and breathe out as you put the pressure on the heels to jump. To absorb the impact, land softly on the toes and balls of the feet and with your knees slightly bent.",
            "Open your chest, roll your shoulders back and keep your spine aligned. Engage your core, inhale as you squat, and breathe out as you squeeze your glutes and stand back up.",
            "Dumbbell thrusters are not appropriate for beginners and you need to pay special attention to your form to prevent any injuries. Keep your chest up, the core engaged, the hips back and inhale as you squat. Put pressure on the heels, push yourself back up, and exhale as you press your arms up",
            "Tighten your core, keep your feet and knees pointing in the same direction, and maintain your back neutral and aligned. Breathe in as you squat, keep the movement slow and controlled, and exhale as you stand back up.",
            "Keep the movement smooth and steady and maintain a slow and deep breathing pattern. Open your chest, keep your back straight and face front.",
            "Keep your core tight and, as you jump, push from your back toes and land softly on your front heel. Move your arms in a way that resembles the running motion and keep your breathing pattern slow and steady.")

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
