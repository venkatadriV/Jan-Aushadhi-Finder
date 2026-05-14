package com.janaushadhi.finder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Medicine entity representing a medicine record in the database.
 * Contains both branded and generic medicine information with pricing.
 */
@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** Branded/commercial name of the medicine (e.g., "Crocin") */
    val brandName: String,

    /** Generic/scientific name of the medicine (e.g., "Paracetamol") */
    val genericName: String,

    /** Salt/chemical composition (e.g., "Paracetamol 500mg") */
    val saltComposition: String,

    /** Price of the branded medicine in INR */
    val brandPrice: Double,

    /** Price of the generic equivalent in INR */
    val genericPrice: Double,

    /** Category of the medicine (e.g., "Pain Relief", "Antibiotics") */
    val category: String,

    /** Whether this is an emergency/essential medicine */
    val isEmergency: Boolean = false,

    /** Manufacturer of the branded medicine */
    val manufacturer: String = "",

    /** Brief description of the medicine and its uses */
    val description: String = ""
) {
    /** Calculate the absolute savings in INR */
    val savings: Double
        get() = brandPrice - genericPrice

    /** Calculate the savings percentage */
    val savingsPercentage: Double
        get() = if (brandPrice > 0) ((brandPrice - genericPrice) / brandPrice) * 100 else 0.0
}
