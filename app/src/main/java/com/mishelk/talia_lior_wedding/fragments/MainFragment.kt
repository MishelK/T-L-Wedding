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
import com.mishelk.talia_lior_wedding.data.RiddleRepository
import com.mishelk.talia_lior_wedding.data_classes.Present
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_presents.*


class MainFragment: Fragment() {

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_main, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProgressBar()
    }

    private fun initProgressBar() {
        val riddleCount = RiddleRepository.getRiddleCount(requireContext())
        val solvedRiddleCount = RiddleRepository.getSolvedRiddleCount(requireContext())
        circularProgressBar.progressMax = riddleCount.toFloat()
        circularProgressBar.progress = solvedRiddleCount.toFloat()
        tvProgress.text = "$solvedRiddleCount/$riddleCount"
    }
}