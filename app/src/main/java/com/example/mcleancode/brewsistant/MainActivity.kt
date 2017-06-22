package com.example.mcleancode.brewsistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    var styleInput: String = ""
    var sugarTypeInput: String = ""
    var temperatureInput: String = ""
    var volumeInput: String = ""

    val primingSugarCalculator = PrimingSugarCalculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPrimingSugarStyleSpinner()
        setupPrimingSugarSugarSpinner()
        setupStyleEvents()
        setupSugarEvents()
        setupTemperatureEvents()
        setupVolumeEvents()
    }

    private fun primingSugarStyleSpinnerView(): Spinner {
        return findViewById(R.id.primingSugarCalculatorStyleField) as Spinner
    }

    private fun primingSugarSugarSpinnerView(): Spinner {
        return findViewById(R.id.primingSugarCalculatorSugarTypeField) as Spinner
    }

    private fun temperatureNumberFieldView(): EditText {
        return findViewById(R.id.primingSugarCalculatorTemperatureField) as EditText
    }

    private fun volumeNumberFieldView(): EditText {
        return findViewById(R.id.primingSugarCalculatorVolumeField) as EditText
    }

    private fun answerGramsTextView(): TextView {
        return findViewById(R.id.primingSugarCalculatorAnswerInGrams) as TextView
    }

    private fun answerOzsTextView(): TextView {
        return findViewById(R.id.primingSugarCalculatorAnswerInOzs) as TextView
    }

    private fun answerCupsTextView(): TextView {
        return findViewById(R.id.primingSugarCalculatorAnswerInCups) as TextView
    }

    private fun setupPrimingSugarStyleSpinner() {
        val spinner = primingSugarStyleSpinnerView()
        val adapter = ArrayAdapter.createFromResource(
                this, R.array.primingSugarCalculatorStyleStrings,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter)
    }

    private fun setupPrimingSugarSugarSpinner() {
        val spinner = primingSugarSugarSpinnerView()
        val adapter = ArrayAdapter.createFromResource(
                this, R.array.primingSugarCalculatorSugarStrings,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter)
    }

    private fun submitForm() {
        if(styleInput == "" || sugarTypeInput == "" || temperatureInput == "" || volumeInput == "") {
            return
        }

        val temperatureDouble = temperatureInput.toDouble()
        val volumeDouble = volumeInput.toDouble()
        val result = primingSugarCalculator.run(temperatureDouble, volumeDouble, styleInput, sugarTypeInput)

        updateAnswerText(result)
    }

    private fun updateAnswerText(beerVolume: BeerVolume) {
        answerGramsTextView().setText(beerVolume.grams().toString())
        answerOzsTextView().setText(beerVolume.ozs().toString())
        answerCupsTextView().setText(beerVolume.cups().toString())
    }

    private fun setupStyleEvents() {
        val primingSugarStyleSpinnerView = primingSugarStyleSpinnerView()

        primingSugarStyleSpinnerView.setOnItemSelectedListener(
                object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                        styleInput = parent.getSelectedItem().toString()
                        submitForm()
                    }

                    override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                        // Do nothing.
                    }
                }
        )
    }

    private fun setupSugarEvents() {
        val primingSugarSugarSpinnerView = primingSugarSugarSpinnerView()

        primingSugarSugarSpinnerView.setOnItemSelectedListener(
                object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                        sugarTypeInput = parent.getSelectedItem().toString()
                        submitForm()
                    }

                    override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                        // Do nothing.
                    }
                }
        )
    }

    private fun setupTemperatureEvents() {
        val temperatureNumberFieldView = temperatureNumberFieldView()

        temperatureNumberFieldView.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                temperatureInput = temperatureNumberFieldView.text.toString()
                submitForm()
            }
        }
    }

    private fun setupVolumeEvents() {
        val volumeNumberFieldView = volumeNumberFieldView()

        volumeNumberFieldView.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                volumeInput = volumeNumberFieldView.text.toString()
                submitForm()
            }
        }
    }
}
