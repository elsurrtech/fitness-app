package com.quiqle.quiqlefitness.legs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class legs_five : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legs_five)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, Legs::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.l_5_1,R.drawable.l_5_2,R.drawable.l_5_3,R.drawable.l_5_4,R.drawable.l_5_5,R.drawable.l_5_6,R.drawable.l_5_7)
        val txts = arrayOf("Forward Jump Shuffle Back",
            "Frog Jumps",
            "Front and Back Lunges",
            "Half Squat JAB Cross",
            "Gate Swings",
            "Good Mornings",
            "Grasshopers")
        val desc = arrayOf("Keep your hips low, engage your core, and use the momentum created by the movement of the arms to jump higher and longer. Keep your spine neutral, shuffle back on the balls of your feet, and maintain a smooth and steady breathing pattern.",
            "Face front, keep your core engaged and your shoulders back. Point your knees in the same direction as your feet and squat down as low as you can without letting your knees go past your toes. Sit on your heels and jump with your arms back. Land softly on your toes and exhale as you squat.",
            "Maintain your back straight, keep your shoulders back and tighten the abs. Breathe in as you lunge and keep your weight in the front heel. Breathe out as you push back up to the starting position, and maintain your feet hip-width apart throughout the entire exercise.",
            "Keep your hips back, your chest up, relax your neck and distribute your weight equally between both legs. Maintain a slow and steady breathing pattern, don’t let your knees extend beyond your toes and don’t extend your arms completely.",
            "Keep your core tight, your back straight, and maintain your knees soft and in line with your toes. Breathe out as you jump, and land slowly on the balls of your feet.",
            "Keep your back tight, shoulder blades pinched together, and your knees slightly bent. Do the movement slowly and breathe out as you lower your torso. Breathe in as you get back up and squeeze the glutes at the end of each repetition.",
            "Keep your spine neutral, point the feet toward the ceiling and exhale as you lift the thighs off the floor and squeeze the glutes. Keep the movement slow and controlled and breathe in as you lower your thighs back to the starting position.")

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
