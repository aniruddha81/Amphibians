package com.aniruddha81.amphibians

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    val id : Int = getNextId(),
    val name: String = "",
    val type: String = "",
    val description: String = "",
    @SerialName(value = "img_src")
    val imgSrc: String = ""
) {
    companion object {
        private var currentId = 0

        fun getNextId(): Int {
            return ++currentId
        }
    }
}