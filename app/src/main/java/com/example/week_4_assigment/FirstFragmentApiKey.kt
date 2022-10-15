package com.example.week_4_assigment

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.week_4_assigment.api.WeatherService
import com.example.week_4_assigment.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class FirstFragmentApiKey : Fragment() {

    private lateinit var navController: NavController
    private lateinit var apiKeyText : TextView
    private lateinit var enterButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_api_key, container, false)
        apiKeyText = view.findViewById(R.id.apiKey)
        enterButton = view.findViewById(R.id.enterButton)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        enterButton.setOnClickListener {
            val weatherCall = checkWeather()
            weatherCall.enqueue(object : Callback<Weather?>{
                override fun onResponse(call: Call<Weather?>?, response: Response<Weather?>) {
                    val weather = response.body()

                    val date = Date(System.currentTimeMillis())
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val time = dateFormat.format(date)



                    passToSecondFragment(weather?.current?.temp?.minus(273)?.toInt(), time )
                }

                override fun onFailure(call: Call<Weather?>?, t: Throwable?) {
                    // Log error here since request failed
                }
            })
        }
    }

    private fun checkWeather() : Call<Weather>{
        val apiKey = apiKeyText.text.toString()
        val lon = "29.257639"
        val lat = "40.879848"
        val excludes = "minutely,hourly,daily"

        // val baseUrl = "https://api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lon&appid=$apiKey"
        val baseUrl = "https://api.openweathermap.org/data/2.5/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java)

        return weatherService.weatherList(lat, lon, apiKey,excludes)
    }

    fun passToSecondFragment(temperature: Int?, time: String) {
        navController.navigate(R.id.action_firstFragmentApiKey_to_secondFragmentWeather, Bundle().apply {
            putString("temperature", temperature.toString())
            putString("time", time)
        })
    }
}