package com.mishelk.talia_lior_wedding.data_classes

data class Present(
    var id: Int = 0,
    var riddleId: Int = 0,
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    var secondaryUrl: String? = "",
    var isUnlocked: Boolean = false,
    var presentType: Int = 1
) {

    enum class PresentType(val id: Int) {
        VIDEO(1),
        VOUCHER(2),
        GIFT(3)
    }
}