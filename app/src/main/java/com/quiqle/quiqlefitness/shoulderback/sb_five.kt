package com.quiqle.quiqlefitness.shoulderback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.quiqle.quiqlefitness.*

class sb_five : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sb_five)
        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, ShoulderBack::class.java)
            startActivity(intent)
        }


        //initialise arrays for inflator
        val gifs = arrayOf(R.drawable.sb_5_1,R.drawable.sb_5_2,R.drawable.sb_5_3,R.drawable.sb_5_4,R.drawable.sb_5_5,R.drawable.sb_5_6,R.drawable.sb_5_7)
        val txts = arrayOf("Dead Lift Upright",
            "Donkey Kick Twist",
            "Dumbbell Side Swings",
            "Lawnmower Band Pull",
            "DumbBell Overhead Rainbow",
            "DumbBell Punches",
            "DumbBell Snatch")
        val desc = arrayOf("Keep your back straight, open the chest and pull your shoulders back. Inhale as you lower the dumbbells, and keep your legs straight or only slightly bent. Push through the heels to get back up, squeeze the glutes and breathe out as you pull the dumbbells toward the chest.",
            "Keep your head, neck and spine neutral, relax your shoulders and engage your core. Breathe in as you extend your leg, and twist your torso only from the ribs up. Exhale as you bring the knee toward the elbow and crunch.",
            "Keep your back flat, your neck relaxed, open the chest and breathe out as you slowly lift the dumbbells. Squeeze your shoulders and your back at the top of the lift, maintain your core tight, and inhale as you lower the dumbbells back to the initial position.",
            "Relax your head and neck, engage your core muscles, maintain your back straight and keep your feet and knees pointing in the same direction. Exhale as you pull the resistance band toward your shoulder and inhale as you release tension and bend the knees.",
            "Tighten your core, keep your feet and knees pointing in the same direction, and keep your back flat and in line with your neck and head. Exhale as you raise the dumbbells above your head and breathe in as you rotate to the sides and lower the dumbbells.",
            "Distribute your weight equally between both feet and shift the weight onto the balls of the feet. Roll your shoulders forward, keep your chin down and look up. Exhale as you push one arm out and don’t extend your arm completely.",
            "Keep your core engaged, relax your neck and back and face forward. Keep the movement dynamic and continuous, and breathe out as you punch straight up and extend your body.")

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
