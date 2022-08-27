package com.mishelk.talia_lior_wedding.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.data_classes.Present
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class PresentRepository {

    companion object {

        fun savePresentsData(presents: MutableList<Present>, context: Context) {
            val gson = Gson()
            val stringJson = gson.toJson(presents)
            val prefs = context.getSharedPreferences("TLPrefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("presents_json", stringJson)
            editor.commit()
        }

        fun getPresents(context: Context): MutableList<Present> {
            val presentsFromCache: MutableList<Present>? = getPresentsDataFromSp(context)
            return presentsFromCache ?: getInitialPresentsData(context)
        }

        fun getUnlockedPresents(context: Context): MutableList<Present> {
            val allPresents: MutableList<Present> = ArrayList(getPresents(context))
            val unlockedPresents: MutableList<Present> = ArrayList()
            for (present in allPresents)
                if (present.isUnlocked)
                    unlockedPresents.add(present)
            return unlockedPresents
        }

        fun getPresent(context: Context, presentId: Int): Present? {
            val presents = getPresents(context)
            for (present in presents) {
                if (present.id == presentId)
                    return present
            }
            return null
        }

        fun unlockPresent(presentId: Int, context: Context): MutableList<Present> {
            val presents = getPresents(context)
            for (present in presents) {
                if (present.id == presentId) {
                    present.isUnlocked = true
                    savePresentsData(presents, context)
                }
            }
            return presents
        }

        private fun getPresentsDataFromSp(context: Context): MutableList<Present>? {
            val prefs = context.getSharedPreferences("TLPrefs", Context.MODE_PRIVATE)
            val stringJson = prefs.getString("presents_json", null)
            val gson = Gson()
            val itemType = object : TypeToken<MutableList<Present>>() {}.type
            return gson.fromJson(stringJson, itemType)
        }

        private fun getInitialPresentsData(context: Context): MutableList<Present> {
            val gson = Gson()
            val itemType = object : TypeToken<MutableList<Present>>() {}.type
            val i: InputStream = context.resources.openRawResource(R.raw.initial_presents_data)
            val br = BufferedReader(InputStreamReader(i))
            val presents: MutableList<Present> = gson.fromJson(br, itemType)
            savePresentsData(presents, context)
            return presents
        }
    }
}