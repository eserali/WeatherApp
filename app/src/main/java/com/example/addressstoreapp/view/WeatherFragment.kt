package com.example.addressstoreapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.service.autofill.UserData
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.addressstoreapp.R
import com.example.addressstoreapp.adapter.HourlyWeatherAdapter
import com.example.addressstoreapp.adapter.WeatherAdapter
import com.example.addressstoreapp.databinding.FragmentWeatherBinding
import com.example.addressstoreapp.manager.HourlyWeatherManager
import com.example.addressstoreapp.manager.WeatherManager
import com.example.addressstoreapp.room.UserDao
import com.example.addressstoreapp.room.UserDatabase
import com.example.addressstoreapp.room.UserEntity


class WeatherFragment : Fragment() {
    private lateinit var binding : FragmentWeatherBinding
    private lateinit var userDb : UserDatabase
    private lateinit var userDao : UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun loadGif(status : Int) {
        binding.ivWeatherGif.setImageResource(status)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater,container,false)

        val hourlyWeatherManager = HourlyWeatherManager()
        val weatherManager = WeatherManager()
        val language = getString(R.string.language)
        userDb = Room.databaseBuilder(requireContext(),UserDatabase::class.java,"UserDatabase").allowMainThreadQueries().build()
        userDao = userDb.userDao()
        val selectedCity = userDao.getUser()[0].selectedCities!!

        val weatherList = weatherManager.getWeather("$selectedCity","$language")

        binding.txtCity.text = selectedCity
        binding.txtDegree.text = "${weatherList[0].degree.toDouble().toInt()}"
        val weatherStatus = weatherList[0].status
        when(weatherStatus) {
            "Snow" -> binding.txtStatus.text = getString(R.string.snow)
            "Clouds" -> binding.txtStatus.text = getString(R.string.clouds)
            "Clear" -> binding.txtStatus.text = getString(R.string.clear)
            "Rain" -> binding.txtStatus.text = getString(R.string.rain)
        }
        binding.txtMinDegree.text = "${weatherList[0].min!!.toDouble().toInt()} °C"
        binding.txtMaxDegree.text = "${weatherList[0].max!!.toDouble().toInt()} °C"

        //val weather = "Clouds"

        //weatherList[0].status
        when(weatherList[0].status) {
            "Rain" -> loadGif(R.drawable.rain)
            "Snow" -> loadGif(R.drawable.snow)
            "Clouds" -> loadGif(R.drawable.clouds)
            "Clear" ->  loadGif(R.drawable.clear)
            else -> loadGif(R.drawable.clear)
        }

        val weatherAdapter = WeatherAdapter(requireContext(),weatherList)
        binding.weatherRcv.adapter = weatherAdapter
        binding.weatherRcv.layoutManager = LinearLayoutManager(context)

        val hourlyWeatherList = hourlyWeatherManager.getHourlyWeather(selectedCity)
        val hourlyWeatherAdapter = HourlyWeatherAdapter(requireContext(),hourlyWeatherList)
        binding.hourlyWeatherRcv.adapter = hourlyWeatherAdapter
        binding.hourlyWeatherRcv.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)

        return binding.root
    }

}