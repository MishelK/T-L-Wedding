package com.mishelk.talia_lior_wedding.data_classes

import org.json.JSONObject

data class Riddle(
    var id: Int = 0,
    var presentId: Int = 0,
    var title: String? = "",
    var description: String? = "",
    var answer: String = "",
    var isSolved: Boolean = false
)
