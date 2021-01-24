package com.quiqle.quiqlefitness.shoulderback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class sb_three : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sb_three)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ShoulderBack::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.sb_3_1,R.drawable.sb_3_2,R.drawable.sb_3_3,R.drawable.sb_3_4,R.drawable.sb_3_5,R.drawable.sb_3_6,R.drawable.sb_3_7)
        val txts = arrayOf("Big Arm Circles",
            "BreakDancer Kick",
            "Butterfly Dips",
            "Core Control Rear Lunge",
            "Bent Over Row Press",
            "Bird Dogs",
            "Bow and Arrow Pull")
        val desc = arrayOf("Stand with your spine nice and tall, inhale as you lift the arms and exhale as you rotate the arms around. Remember to engage your core muscles and keep your back and arms straight. With every breath you take your opening up the shoulders and chest and warming up the body. It’s important to keep this movement slow and controlled so that you can fully feel the stretch.",
            "Start on all fours, with your wrists underneath your shoulders and your knees underneath your hips. Lift your knees off the floor, engage your core, and keep your back, neck, and head neutral. Exhale as you rotate your hips to the left and kick your right leg under your body.",
            "Roll your shoulders back, open the chest, position the hands under your shoulders, place your heels together and let your knees fall open. Breathe out as you press your hips up and extend your arms. Pause at the top to squeeze your legs in, and inhale as you bend your arms and return to the initial position.",
            "Engage your core for stability, face front, open your chest and keep your back straight. As you lunge, keep the front knee aligned with the ankle and keep your weight loaded in the front heel. Exhale as you stand up and maintain a smooth and steady rhythm.",
            "Keep your shoulders back, maintain your back straight, tighten your core and keep your head up. Inhale as you pull the dumbbell toward the waistline and keep the elbow close to your body. Rotate your torso to the right, face up and exhale as you push the dumbbell up and over your head.",
            "When doing bird dogs keep your spine in a neutral position, with your head and neck in alignment, and relax your shoulders. Breathe in as you lift your arm and the opposite leg, and maintain your torso stable by engaging your core",
            "Keep your back straight, with your head and neck relaxed and in line, bend your knees and maintain your elbow joints loose and slightly bent. Breathe slowly, engage your core, don’t let your knees move past your toes, and twist your torso only from the ribs up.")

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
