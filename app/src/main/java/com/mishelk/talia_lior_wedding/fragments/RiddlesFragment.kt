package com.mishelk.talia_lior_wedding.fragments

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.adapters.RiddleAdapter
import com.mishelk.talia_lior_wedding.data_classes.Riddle
import kotlinx.android.synthetic.main.fragment_riddles.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class RiddlesFragment: Fragment() {

    private var riddles: MutableList<Riddle> = ArrayList()

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Defines the xml file for the fragment
        riddles = getRiddlesData()
        saveRiddlesData()
        return inflater.inflate(R.layout.fragment_riddles, parent, false)
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvContent.layoutManager = LinearLayoutManager(context)
        rvContent.adapter = RiddleAdapter(context ?: return, riddles)
    }

    private fun saveRiddlesData() {
        val gson = Gson()
        val stringJson = gson.toJson(riddles)
        val prefs = requireContext().getSharedPreferences("TLPrefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("riddles_json", stringJson)
        editor.commit()
    }

    private fun getRiddlesData(): MutableList<Riddle> {
        val riddlesFromCache: MutableList<Riddle>? = getRiddlesDataFromSp()
        return riddlesFromCache ?: getInitialRiddlesData()
    }

    private fun getRiddlesDataFromSp(): MutableList<Riddle>? {
        val prefs = requireContext().getSharedPreferences("TLPrefs", MODE_PRIVATE)
        val stringJson = prefs.getString("riddles_json", null)
        val gson = Gson()
        val itemType = object : TypeToken<MutableList<Riddle>>() {}.type
        return gson.fromJson(stringJson, itemType)
    }

    private fun getInitialRiddlesData(): MutableList<Riddle> {
        val gson = Gson()
        val itemType = object : TypeToken<MutableList<Riddle>>() {}.type
        val i: InputStream = requireContext().resources.openRawResource(R.raw.initial_riddles_data)
        val br = BufferedReader(InputStreamReader(i))
        return gson.fromJson(br, itemType)
    }
}