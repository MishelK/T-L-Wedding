package com.mishelk.talia_lior_wedding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.adapters.PresentAdapter
import com.mishelk.talia_lior_wedding.bottom_sheets.RiddleBottomSheet
import com.mishelk.talia_lior_wedding.data.PresentRepository
import com.mishelk.talia_lior_wedding.data.RiddleRepository
import com.mishelk.talia_lior_wedding.data_classes.Present
import com.mishelk.talia_lior_wedding.data_classes.Riddle
import kotlinx.android.synthetic.main.fragment_riddles.*

class PresentsFragment: Fragment() {

    private var presents: MutableList<Present> = ArrayList()
    private lateinit var presentAdapter: PresentAdapter

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Defines the xml file for the fragment
        presents = PresentRepository.getPresentsData(requireContext())
        return inflater.inflate(R.layout.fragment_presents, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvContent.layoutManager = LinearLayoutManager(context)
        presentAdapter = PresentAdapter(context ?: return, ArrayList(presents), object: PresentAdapter.OnItemSelectedListener {
            override fun onItemSelected(item: Present, position: Int) {
                showPresentBottomSheet(item, position)
            }
        })
        rvContent.adapter = presentAdapter
    }

    private fun showPresentBottomSheet(present: Present, position: Int) {
//        val riddleBottomSheet = RiddleBottomSheet(present, object: RiddleBottomSheet.OnClickListeners {
//            override fun onRiddleSolved() {
//                riddleAdapter.data = RiddleRepository.solveRiddle(riddles[position].id, requireContext())
//                riddleAdapter.notifyDataSetChanged()
//                // TODO: Go to present
//            }
//        })
//        riddleBottomSheet.show(activity?.supportFragmentManager ?: return, riddleBottomSheet.tag)
    }
}