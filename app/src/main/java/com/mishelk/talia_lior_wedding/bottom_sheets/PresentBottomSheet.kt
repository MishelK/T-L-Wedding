package com.mishelk.talia_lior_wedding.bottom_sheets

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.data_classes.Present


class PresentBottomSheet(
    private val present: Present,
    private val onClickListeners: OnClickListeners?): BaseBottomSheet() {

    private lateinit var llRestaurantVoucher: LinearLayout
    private lateinit var llGift: LinearLayout
    private lateinit var llVideo: LinearLayout
    private lateinit var btnOpenVoucher: CardView
    private lateinit var btnOpenBookingLink: CardView
    private lateinit var btnOpenVideo: CardView
    private lateinit var ivGift: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView

    override fun getContentResource(): Int {
        return R.layout.present_bottom_sheet
    }

    override fun initViews(view: View) {
        llRestaurantVoucher = view.findViewById(R.id.llRestaurantVoucher)
        llGift = view.findViewById(R.id.llGift)
        llVideo = view.findViewById(R.id.llVideo)
        btnOpenVoucher = view.findViewById(R.id.btnOpenVoucher)
        btnOpenBookingLink = view.findViewById(R.id.btnOpenBookingLink)
        btnOpenVideo = view.findViewById(R.id.btnOpenVideo)
        ivGift = view.findViewById(R.id.ivGift)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvDescription = view.findViewById(R.id.tvDescription)
        initViewsByPresentType()
    }

    override fun initTexts() {
        tvTitle.text = present.title
        tvDescription.text = present.description
    }

    override fun initListeners() {
        btnOpenVoucher.setOnClickListener {
            launchUrl(present.url)
        }
        btnOpenBookingLink.setOnClickListener {
            launchUrl(present.reservationUrl)
        }
        btnOpenVideo.setOnClickListener {
            launchUrl(present.url)
        }
    }

    private fun initViewsByPresentType() {
        when(present.presentType) {
            Present.PresentType.VIDEO.id -> {
                llVideo.isVisible = true
            }
            Present.PresentType.VOUCHER.id -> {
                llRestaurantVoucher.isVisible = true
            }
            Present.PresentType.GIFT.id -> {
                llGift.isVisible = true
                Glide.with(requireActivity()).load(present.url).into(ivGift)
            }
        }
    }

    private fun launchUrl(url: String?) {
        if (url == null) return
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            requireActivity().startActivity(webIntent)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
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