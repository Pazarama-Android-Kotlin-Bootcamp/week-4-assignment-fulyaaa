package com.example.week_4_assigment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import com.example.week_4_assigment.model.Current
import com.example.week_4_assigment.model.Weather

class SecondFragmentWeather : Fragment() {

    private lateinit var temperatureTextView : TextView
    private lateinit var timeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View){
        temperatureTextView = view.findViewById(R.id.degreeText)
        timeTextView = view.findViewById(R.id.editTextDate)

        arguments?.let {
            val temperature = it.get("temperature")
            val time = it.get("time")

            temperatureTextView.text = temperature.toString()
            timeTextView.text = time.toString()
        }
    }

}