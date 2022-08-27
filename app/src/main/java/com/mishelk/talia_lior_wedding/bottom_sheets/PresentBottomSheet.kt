package com.mishelk.talia_lior_wedding.bottom_sheets

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.data_classes.Present

class PresentBottomSheet(
    private val present: Present,
    private val onClickListeners: OnClickListeners?): BaseBottomSheet() {

    override fun getContentResource(): Int {
        return R.layout.present_bottom_sheet
    }

    override fun initViews(view: View) {
        initViewsByPresentType()
    }

    override fun initTexts() {

    }

    override fun initListeners() {

    }

    private fun initViewsByPresentType() {

    }

    override fun disabledNativeBackPress(): Boolean {
        return false
    }

    override fun enableDrag(): Boolean {
        return true
    }

    interface OnClickListeners {

    }

}