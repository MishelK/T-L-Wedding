package com.mishelk.talia_lior_wedding.fragments

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.adapters.RiddleAdapter
import com.mishelk.talia_lior_wedding.bottom_sheets.RiddleBottomSheet
import com.mishelk.talia_lior_wedding.data.PresentRepository
import com.mishelk.talia_lior_wedding.data.RiddleRepository
import com.mishelk.talia_lior_wedding.data_classes.Riddle
import kotlinx.android.synthetic.main.fragment_riddles.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class RiddlesFragment: Fragment() {

    private var riddles: MutableList<Riddle> = ArrayList()
    private lateinit var riddleAdapter: RiddleAdapter

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Defines the xml file for the fragment
        riddles = RiddleRepository.getRiddlesData(requireContext())
        return inflater.inflate(R.layout.fragment_riddles, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvContent.layoutManager = LinearLayoutManager(context)
        riddleAdapter = RiddleAdapter(context ?: return, ArrayList(riddles), object: RiddleAdapter.OnItemSelectedListener {
            override fun onItemSelected(item: Riddle, position: Int) {
                showRiddleBottomSheet(item, position)
            }
        })
        rvContent.adapter = riddleAdapter
    }

    private fun showRiddleBottomSheet(riddle: Riddle, position: Int) {
        val riddleBottomSheet = RiddleBottomSheet(riddle, object: RiddleBottomSheet.OnClickListeners {
            override fun onRiddleSolved() {
                PresentRepository.unlockPresent(riddles[position].presentId, requireContext())
                riddleAdapter.data = RiddleRepository.solveRiddle(riddles[position].id, requireContext())
                riddleAdapter.notifyDataSetChanged()
            }

            override fun onGoToPresent() {

            }
        })
        riddleBottomSheet.show(activity?.supportFragmentManager ?: return, riddleBottomSheet.tag)
    }
}