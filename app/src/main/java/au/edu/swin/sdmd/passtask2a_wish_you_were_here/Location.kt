package au.edu.swin.sdmd.passtask2a

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Location(
    val title: String, val city: String,
    val date: String, var rating: Double,
    var visited: Boolean = false
    ): Parcelable {
}