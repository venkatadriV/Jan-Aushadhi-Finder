package com.janaushadhi.finder.data.model

/**
 * Store data class representing a Jan-Aushadhi Kendra.
 * Not stored in Room — uses simulated data.
 */
data class Store(
    val id: Int,

    /** Name of the Jan-Aushadhi Kendra */
    val name: String,

    /** Full address of the store */
    val address: String,

    /** Latitude coordinate */
    val latitude: Double,

    /** Longitude coordinate */
    val longitude: Double,

    /** Phone number of the store */
    val phone: String = "",

    /** Whether the store is currently open (simulated) */
    val isOpen: Boolean = true,

    /** Store rating out of 5 (simulated) */
    val rating: Float = 4.0f,

    /** Distance from user in km (computed at runtime) */
    var distanceKm: Double = 0.0,

    /** Stock availability status: null = not checked, true = available, false = out of stock */
    var stockStatus: Boolean? = null
)
