package com.quiqle.quiqlefitness.shoulderback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class sb_six : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sb_six)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ShoulderBack::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.sb_6_1,R.drawable.sb_6_2,R.drawable.sb_6_3,R.drawable.sb_6_4,R.drawable.sb_6_5,R.drawable.sb_6_6,R.drawable.sb_6_7)
        val txts = arrayOf("DumbBell Thrusters",
            "Elbow Squeeze Shoulder Press",
            "Front Back Punch",
            "Half squat Jab Cross",
            "Mid Back Band Pull",
            "Neck Rolls",
            "Plank Row Leg Raise")
        val desc = arrayOf("Dumbbell thrusters are not appropriate for beginners and you need to pay special attention to your form to prevent any injuries. Keep your chest up, the core engaged, the hips back and inhale as you squat. Put pressure on the heels, push yourself back up, and exhale as you press your arms up.",
            "Keep your back straight, engage the core, open the chest and face front. Breathe out as you bring the elbows and forearms toward the midline of the body, and squeeze the chest. Keep your arms at a 90-degree angle and breathe in as you return to the starting position. Exhale as you push the dumbbells up and over your head, and maintain your hands shoulder-width apart.",
            "Maintain your feet hip-width apart, knees slightly bent and keep your spine neutral with your head, neck and back in line and relaxed. Distribute your weight equally between both feet, breathe out as you punch, and keep your torso static and your core engaged.",
            "Keep your hips back, your chest up, relax your neck and distribute your weight equally between both legs. Maintain a slow and steady breathing pattern, don’t let your knees extend beyond your toes and don’t extend your arms completely.",
            "Keep your back, head and neck relaxed, maintain your arms long with just a slight bend in the elbow and keep your shoulders away from the ears. Exhale as pull the band to the sides and inhale as you return to the starting position.",
            "Maintain your shoulders relaxed and keep the movements big, slow and fluid. Breathe deeply and stretch the neck gently without letting it fall too far backward. If your neck is too weak or if you feel any discomfort you can start with a semicircular movement. Drop the chin toward the chest and roll your head to the left, roll it back to the front and around to the right shoulder.",
            "Lock your wrists to protect the joints, keep your core tight, engage your glutes and maintain your body in a straight line. Breathe out as you pull the dumbbells toward the waistline and breathe in as you lower the legs to return into plank position.")

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
