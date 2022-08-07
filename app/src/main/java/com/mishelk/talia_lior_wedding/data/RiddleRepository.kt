package com.mishelk.talia_lior_wedding.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.data_classes.Riddle
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class RiddleRepository {

    companion object {

        fun saveRiddlesData(riddles: MutableList<Riddle>, context: Context) {
            val gson = Gson()
            val stringJson = gson.toJson(riddles)
            val prefs = context.getSharedPreferences("TLPrefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("riddles_json", stringJson)
            editor.commit()
        }

        fun getRiddlesData(context: Context): MutableList<Riddle> {
            val riddlesFromCache: MutableList<Riddle>? = getRiddlesDataFromSp(context)
            return riddlesFromCache ?: getInitialRiddlesData(context)
        }

        private fun getRiddlesDataFromSp(context: Context): MutableList<Riddle>? {
            val prefs = context.getSharedPreferences("TLPrefs", Context.MODE_PRIVATE)
            val stringJson = prefs.getString("riddles_json", null)
            val gson = Gson()
            val itemType = object : TypeToken<MutableList<Riddle>>() {}.type
            return gson.fromJson(stringJson, itemType)
        }

        private fun getInitialRiddlesData(context: Context): MutableList<Riddle> {
            val gson = Gson()
            val itemType = object : TypeToken<MutableList<Riddle>>() {}.type
            val i: InputStream = context.resources.openRawResource(R.raw.initial_riddles_data)
            val br = BufferedReader(InputStreamReader(i))
            return gson.fromJson(br, itemType)
        }
    }
}