package com.janaushadhi.finder.util

import com.janaushadhi.finder.data.model.Medicine
import kotlin.math.min

/**
 * Fuzzy search utility using Levenshtein distance.
 * Handles spelling mistakes in medicine name searches.
 */
object FuzzySearch {

    /** Search medicines with fuzzy matching, returns results sorted by relevance */
    fun search(query: String, medicines: List<Medicine>, maxDistance: Int = 3): List<Medicine> {
        val q = query.lowercase().trim()
        if (q.isEmpty()) return emptyList()

        return medicines.mapNotNull { medicine ->
            val brandDist = minDistance(q, medicine.brandName.lowercase())
            val genericDist = minDistance(q, medicine.genericName.lowercase())
            val saltDist = minDistance(q, medicine.saltComposition.lowercase())
            val bestDist = minOf(brandDist, genericDist, saltDist)
            if (bestDist <= maxDistance) Pair(medicine, bestDist) else null
        }.sortedBy { it.second }
         .map { it.first }
    }

    /** Calculate minimum edit distance considering substring matches */
    private fun minDistance(query: String, target: String): Int {
        // Check substring containment first
        if (target.contains(query)) return 0
        if (query.length <= 2) return levenshtein(query, target)

        // Check against each word in the target
        val words = target.split(" ", "-", "+", "/")
        var minDist = levenshtein(query, target)
        for (word in words) {
            val dist = levenshtein(query, word.lowercase())
            minDist = min(minDist, dist)
        }
        // Also check prefix matching
        if (target.startsWith(query.take(3))) {
            minDist = min(minDist, query.length - 3)
        }
        return minDist
    }

    /** Standard Levenshtein distance algorithm */
    private fun levenshtein(s1: String, s2: String): Int {
        val m = s1.length
        val n = s2.length
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) dp[i][0] = i
        for (j in 0..n) dp[0][j] = j
        for (i in 1..m) {
            for (j in 1..n) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                dp[i][j] = minOf(dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + cost)
            }
        }
        return dp[m][n]
    }
}
