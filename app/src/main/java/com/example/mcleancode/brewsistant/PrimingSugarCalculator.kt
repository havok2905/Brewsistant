package com.example.mcleancode.brewsistant

class PrimingSugarCalculator() {
    fun run(temperature: Double, gallons: Double, style: String, sugarType: String): BeerVolume {
        val co2 = convertCo2(style)
        val sugarEfficiency = convertSugarEfficiency(sugarType)
        return formula(temperature, gallons, co2, sugarEfficiency)
    }

    private fun formatedString(value: String): String {
        return value.replace(" ", "")
    }

    private fun convertCo2(style: String): Double {
        val key = formatedString(style)
        return Styles.valueOf(key).co2
    }

    private fun convertSugarEfficiency(sugarType: String): Double {
        val key = formatedString(sugarType)
        return Sugars.valueOf(key).efficiency
    }

    private fun formula(temperature: Double, gallons: Double, co2: Double, sugarEfficiency: Double): BeerVolume {
        val pureSugarVolume = 15.195 * gallons * ( co2 - 3.0378 + ( 0.050062 * temperature ) - ( 0.0002655 * Math.pow(temperature, 2.0 ) ) )
        val resultInGrams = pureSugarVolume / sugarEfficiency

        return BeerVolume(resultInGrams)
    }
}
