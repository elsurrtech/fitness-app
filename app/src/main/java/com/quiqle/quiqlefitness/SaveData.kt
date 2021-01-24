package com.quiqle.quiqlefitness

import android.content.Context
import android.content.SharedPreferences

class SaveData(context: Context) {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("file",Context.MODE_PRIVATE)

    //This method will save the night state mode :True or :False
    fun setDarkModeState(state: Boolean?){
        val editor = sharedPreferences.edit()
        editor.putBoolean("Dark",state!!)
        editor.apply()
    }

    //This fun will load night mode state
    fun loadDarkModeState(): Boolean? {
        val state = sharedPreferences.getBoolean("Dark",false)
        return (state)
    }
}