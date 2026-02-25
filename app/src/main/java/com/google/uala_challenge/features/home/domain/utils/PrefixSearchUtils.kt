package com.google.uala_challenge.features.home.domain.utils

import com.google.uala_challenge.features.home.domain.model.CityModel

fun findRangeByPrefix(
    sortedCities: List<CityModel>,
    prefix: String
): IntRange {
    if (prefix.isEmpty()) return sortedCities.indices
    if (sortedCities.isEmpty()) return IntRange.EMPTY

    val lowerQuery = prefix.lowercase().trim()

    val start = findLowerBound(sortedCities, lowerQuery)
    val end = findLowerBound(sortedCities, lowerQuery + "\uFFFF")

    return start until end
}

private fun findLowerBound(
    sortedCities: List<CityModel>,
    target: String
): Int {
    var low = 0
    var high = sortedCities.size
    while (low < high) {
        val mid = (low + high) ushr 1
        val key = sortedCities[mid].searchKey
        if (key < target) low = mid + 1
        else high = mid
    }
    return low
}

