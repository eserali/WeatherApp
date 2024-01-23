package com.example.addressstoreapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.addressstoreapp.databinding.HourlyWeatherRcvRowBinding
import com.example.addressstoreapp.response.HourlyWeatherResponse
import com.example.addressstoreapp.manager.WeatherCodeStatusManager

class HourlyWeatherAdapter(val context : Context,val hourlyWeatherList : List<HourlyWeatherResponse>) : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder>() {

    class HourlyWeatherViewHolder(var hourlyWeatherRecvRowBinding: HourlyWeatherRcvRowBinding) : RecyclerView.ViewHolder(hourlyWeatherRecvRowBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val binding = HourlyWeatherRcvRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HourlyWeatherViewHolder(binding)

    }

    override fun getItemCount(): Int {
         return hourlyWeatherList.size
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        val binding = holder.hourlyWeatherRecvRowBinding
        val hourlyWeather = hourlyWeatherList[position]
        binding.txtHour.text = "${hourlyWeather.time}:00"
        val temperature = hourlyWeather.hourlyWeatherValuesResponse.temperature.toInt()
        binding.txtHourlyDegree.text = "${temperature} °C"

        val status = hourlyWeather.hourlyWeatherValuesResponse.weatherCode
        val weatherCodeStatusManagerManager = WeatherCodeStatusManager(status)
        binding.ivHourlyStatus.setImageResource(weatherCodeStatusManagerManager.detectWeatherCode())


    }
}