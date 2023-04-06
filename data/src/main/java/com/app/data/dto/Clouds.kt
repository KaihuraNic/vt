package com.app.data.dto

import com.google.gson.annotations.SerializedName

data class Clouds(
    @field:SerializedName("all") var all: Int? = null
)