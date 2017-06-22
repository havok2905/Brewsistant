package com.example.mcleancode.brewsistant

class BeerVolume(val grams: Double) {
    fun grams(): Double {
        return grams
    }

    fun ozs(): Double {
        return grams * 0.035274
    }

    fun cups(): Double {
        return grams * 0.00422675281986
    }
}