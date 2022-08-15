package com.mishelk.talia_lior_wedding.bottom_sheets

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.data_classes.Riddle

class RiddleBottomSheet(
    private val riddle: Riddle,
    private val onClickListeners: OnClickListeners?): BaseBottomSheet() {

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var etAnswer: EditText
    private lateinit var btnSubmitAnswer: CardView

    override fun getContentResource(): Int {
        return R.layout.riddle_bottom_sheet
    }

    override fun initViews(view: View) {
        tvTitle = view.findViewById(R.id.tvTitle)
        tvDescription = view.findViewById(R.id.tvDescription)
        etAnswer = view.findViewById(R.id.etAnswer)
        btnSubmitAnswer = view.findViewById(R.id.btnSubmitAnswer)
    }

    override fun initTexts() {
        tvTitle.text = riddle.title
        tvDescription.text = riddle.description
    }

    override fun initListeners() {
        btnSubmitAnswer.setOnClickListener {
            onClickListeners?.onSubmitAnswer(etAnswer.text.toString())
        }
    }

    override fun disabledNativeBackPress(): Boolean {
        return false
    }

    override fun enableDrag(): Boolean {
        return true
    }

    interface OnClickListeners {
        fun onSubmitAnswer(answer: String)
    }
}