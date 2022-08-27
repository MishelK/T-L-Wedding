package com.mishelk.talia_lior_wedding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.adapters.PresentAdapter
import com.mishelk.talia_lior_wedding.bottom_sheets.PresentBottomSheet
import com.mishelk.talia_lior_wedding.data.PresentRepository
import com.mishelk.talia_lior_wedding.data_classes.Present
import kotlinx.android.synthetic.main.fragment_presents.*
import kotlinx.android.synthetic.main.fragment_presents.rvContent

class PresentsFragment: Fragment() {

    private var presents: MutableList<Present> = ArrayList()
    private lateinit var presentAdapter: PresentAdapter

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Defines the xml file for the fragment
        presents = PresentRepository.getUnlockedPresents(requireContext())
        return inflater.inflate(R.layout.fragment_presents, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (presents.isEmpty()) {
            tvNoPresents.isVisible = true
            rvContent.isVisible = false
        }
        rvContent.layoutManager = LinearLayoutManager(context)
        presentAdapter = PresentAdapter(context ?: return, ArrayList(presents), object: PresentAdapter.OnItemSelectedListener {
            override fun onItemSelected(item: Present, position: Int) {
                showPresentBottomSheet(item)
            }
        })
        rvContent.adapter = presentAdapter
    }

    private fun showPresentBottomSheet(present: Present) {
        val presentBottomSheet = PresentBottomSheet(present, object: PresentBottomSheet.OnClickListeners {

        })
        presentBottomSheet.show(activity?.supportFragmentManager ?: return, presentBottomSheet.tag)
    }
}