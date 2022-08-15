package com.mishelk.talia_lior_wedding.bottom_sheets

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.data_classes.Riddle

class RiddleBottomSheet(
    private val riddle: Riddle,
    private val onClickListeners: OnClickListeners?): BaseBottomSheet() {

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvError: TextView
    private lateinit var etAnswer: EditText
    private lateinit var btnSubmitAnswer: CardView

    override fun getContentResource(): Int {
        return R.layout.riddle_bottom_sheet
    }

    override fun initViews(view: View) {
        tvTitle = view.findViewById(R.id.tvTitle)
        tvDescription = view.findViewById(R.id.tvDescription)
        tvError = view.findViewById(R.id.tvError)
        etAnswer = view.findViewById(R.id.etAnswer)
        btnSubmitAnswer = view.findViewById(R.id.btnSubmitAnswer)
    }

    override fun initTexts() {
        tvTitle.text = riddle.title
        tvDescription.text = riddle.description
    }

    override fun initListeners() {
        btnSubmitAnswer.setOnClickListener {
            onAnswerSubmitted(etAnswer.text.toString())
        }
        etAnswer.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                tvError.isInvisible = true
            }
        })
    }

    private fun onAnswerSubmitted(answer: String) {
        val didSolve = validateAnswer(answer)
        if (didSolve) {
            onClickListeners?.onRiddleSolved()
            dismiss()
        } else {
            tvError.isInvisible = false
        }
    }

    private fun validateAnswer(answer: String): Boolean {
        return riddle.answer == answer
    }

    override fun disabledNativeBackPress(): Boolean {
        return false
    }

    override fun enableDrag(): Boolean {
        return true
    }

    interface OnClickListeners {
        fun onRiddleSolved()
    }

}