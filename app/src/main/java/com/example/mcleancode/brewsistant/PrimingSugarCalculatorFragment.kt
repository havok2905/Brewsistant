package com.example.mcleancode.brewsistant

import android.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class PrimingSugarCalculatorFragment: Fragment() {
    var styleInput: String = ""
    var sugarTypeInput: String = ""
    var temperatureInput: String = ""
    var volumeInput: String = ""

    val primingSugarCalculator = PrimingSugarCalculator()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstance: Bundle?): View {
        val v = inflater.inflate(R.layout.priming_sugar_calculator_fragment, container, false)

        setupPrimingSugarStyleSpinner(v)
        setupPrimingSugarSugarSpinner(v)
        setupStyleEvents(v)
        setupSugarEvents(v)
        setupTemperatureEvents(v)
        setupVolumeEvents(v)

        return v
    }

    private fun primingSugarStyleSpinnerView(v: View): Spinner {
        return v.findViewById(R.id.primingSugarCalculatorStyleField) as Spinner

    }

    private fun primingSugarSugarSpinnerView(v: View): Spinner {
        return v.findViewById(R.id.primingSugarCalculatorSugarTypeField) as Spinner
    }

    private fun temperatureNumberFieldView(v: View): EditText {
        return v.findViewById(R.id.primingSugarCalculatorTemperatureField) as EditText
    }

    private fun volumeNumberFieldView(v: View): EditText {
        return v.findViewById(R.id.primingSugarCalculatorVolumeField) as EditText
    }

    private fun answerGramsTextView(v: View): TextView {
        return v.findViewById(R.id.primingSugarCalculatorAnswerInGrams) as TextView
    }

    private fun answerOzsTextView(v: View): TextView {
        return v.findViewById(R.id.primingSugarCalculatorAnswerInOzs) as TextView
    }

    private fun answerCupsTextView(v: View): TextView {
        return v.findViewById(R.id.primingSugarCalculatorAnswerInCups) as TextView
    }

    private fun setupPrimingSugarStyleSpinner(v: View) {
        val spinner = primingSugarStyleSpinnerView(v)
        val adapter = ArrayAdapter.createFromResource(
                activity, R.array.primingSugarCalculatorStyleStrings,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.adapter = adapter
    }

    private fun setupPrimingSugarSugarSpinner(v: View) {
        val spinner = primingSugarSugarSpinnerView(v)
        val adapter = ArrayAdapter.createFromResource(
                activity, R.array.primingSugarCalculatorSugarStrings,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.adapter = adapter
    }

    private fun submitForm(view: View) {
        if(styleInput == "" || sugarTypeInput == "" || temperatureInput == "" || volumeInput == "") {
            return
        }

        val temperatureDouble = temperatureInput.toDouble()
        val volumeDouble = volumeInput.toDouble()
        val result = primingSugarCalculator.run(temperatureDouble, volumeDouble, styleInput, sugarTypeInput)

        updateAnswerText(view, result)
    }

    private fun updateAnswerText(v: View, beerVolume: BeerVolume) {
        answerGramsTextView(v).text = beerVolume.grams.toString()
        answerOzsTextView(v).text = beerVolume.ozs.toString()
        answerCupsTextView(v).text = beerVolume.cups.toString()
    }

    private fun setupStyleEvents(v: View) {
        val primingSugarStyleSpinnerView = primingSugarStyleSpinnerView(v)

        primingSugarStyleSpinnerView.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                    styleInput = parent.selectedItem.toString()
                    submitForm(v)
                }

                override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                    // Do nothing.
                }
            }
    }

    private fun setupSugarEvents(v: View) {
        val primingSugarSugarSpinnerView = primingSugarSugarSpinnerView(v)

        primingSugarSugarSpinnerView.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                    sugarTypeInput = parent.selectedItem.toString()
                    submitForm(v)
                }

                override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                    // Do nothing.
                }
            }
    }

    private fun setupTemperatureEvents(v: View) {
        val temperatureNumberFieldView = temperatureNumberFieldView(v)
        temperatureNumberFieldView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                temperatureInput = temperatureNumberFieldView.text.toString()
                submitForm(v)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do Nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do Nothing
            }
        })
    }

    private fun setupVolumeEvents(v: View) {
        val volumeNumberFieldView = volumeNumberFieldView(v)
        volumeNumberFieldView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                volumeInput = volumeNumberFieldView.text.toString()
                submitForm(v)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do Nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do Nothing
            }
        })
    }
}