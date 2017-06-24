package com.example.mcleancode.brewsistant

class BeerVolume(val grams: Double) {
    val ozs: Double
        get() = grams * 0.035274

    val cups: Double
        get() = grams * 0.00422675281986
}