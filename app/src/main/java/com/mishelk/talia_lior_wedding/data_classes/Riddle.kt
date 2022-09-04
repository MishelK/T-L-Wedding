package com.mishelk.talia_lior_wedding.data_classes

import org.json.JSONObject

data class Riddle(
    var id: Int = 0,
    var presentId: Int = 0,
    var title: String? = "",
    var description: String? = "",
    var answers: Array<String> = arrayOf(),
    var isSolved: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Riddle

        if (id != other.id) return false
        if (presentId != other.presentId) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (!answers.contentEquals(other.answers)) return false
        if (isSolved != other.isSolved) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + presentId
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + answers.contentHashCode()
        result = 31 * result + isSolved.hashCode()
        return result
    }
}
