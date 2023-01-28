package com.example.bmsAssignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieScheduleResponse(val venues: List<Venue?>)

@Parcelize
data class Venue(val name: String?, val showDate: String?, @SerializedName("showtimes") var showTimes: List<ShowTimes?>) : Parcelable

@Parcelize data class ShowTimes(val showDateCode: Long?, val showTime: String?) : Parcelable{

    override fun toString(): String {
        return showTime.toString()
    }
}
