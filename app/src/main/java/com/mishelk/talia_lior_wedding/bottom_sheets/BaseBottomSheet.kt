package com.mishelk.talia_lior_wedding.bottom_sheets

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.R.id.design_bottom_sheet
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mishelk.talia_lior_wedding.R

abstract class BaseBottomSheet() : BottomSheetDialogFragment() {

    protected var bottomSheet: FrameLayout? = null
    protected var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getParentLayout(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initListeners()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: BottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            bottomSheet = d.findViewById<View>(design_bottom_sheet) as FrameLayout?
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet as View)
            bottomSheetBehavior.isDraggable = enableDrag()
            if (bottomSheet is FrameLayout) {
                bottomSheetBehavior.peekHeight = bottomSheet!!.height
                bottomSheet!!.parent.parent.requestLayout()
            }
        }

        dialog.setOnKeyListener { _, keyCode, _ -> keyCode == KeyEvent.KEYCODE_BACK && disabledNativeBackPress() }

        return dialog
    }

    protected fun changeDraggableState(isDraggable: Boolean) {
        if (bottomSheet != null) {
            BottomSheetBehavior.from(bottomSheet as View).isDraggable = isDraggable
        }
    }

    protected abstract fun getContentResource(): Int

    protected abstract fun initViews(view: View)

    protected abstract fun initListeners()

    protected abstract fun disabledNativeBackPress(): Boolean

    protected abstract fun enableDrag(): Boolean

    open fun getParentLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ViewGroup {
        val baseView: ViewGroup = inflater.inflate(
            R.layout.bottom_sheet_base,
            container,
            false
        ) as ViewGroup
        val childView: View = inflater.inflate(getContentResource(), container, false)
        baseView.addView(childView)
        return baseView
    }

    protected fun startFadeInAnimation(view: View) {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = 500
        val animation = AnimationSet(false)
        animation.addAnimation(fadeIn)
        view.animation = animation
        view.visibility = View.VISIBLE
    }

    protected fun startFadeOutAnimation(view: View) {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = DecelerateInterpolator()
        fadeOut.duration = 500
        val animation = AnimationSet(false)
        animation.addAnimation(fadeOut)
        view.animation = animation
        view.visibility = View.GONE
    }
}
