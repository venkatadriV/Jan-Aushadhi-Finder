package com.janaushadhi.finder.util

import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

/** Format price as Indian Rupee string */
fun Double.toRupee(): String = "₹%.2f".format(this)

/** Format price as short string without decimals */
fun Double.toRupeeShort(): String = "₹%.0f".format(this)

/** Format percentage */
fun Double.toPercent(): String = "%.0f%%".format(this)

/** Format distance in km */
fun Double.toKmString(): String = "%.1f km".format(this)

/** Format epoch millis to readable date */
fun Long.toDateString(): String {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return sdf.format(Date(this))
}

/** Format epoch millis to readable time */
fun Long.toTimeString(): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date(this))
}

/** Format epoch millis to date and time */
fun Long.toDateTimeString(): String {
    val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
    return sdf.format(Date(this))
}
