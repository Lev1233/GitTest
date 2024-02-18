package com.example.testappsbuilding.model


import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class MarsPhoto @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("img_src") val imgSrc: String
)
//@Serializable
//data class MarsPhoto(
//    val id: String,
//    @SerialName(value = "img_src")
//    val imgSrc: String
//)