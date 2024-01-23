package com.example.addressstoreapp.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.addressstoreapp.R
import com.example.addressstoreapp.databinding.WeatherRcvRowBinding
import com.example.addressstoreapp.response.WeatherResultResponse

class WeatherAdapter(val context : Context, val weatherResultResponseList : List<WeatherResultResponse>) : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    class WeatherHolder(var weatherRcvRowBinding: WeatherRcvRowBinding) : RecyclerView.ViewHolder(weatherRcvRowBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val binding = WeatherRcvRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WeatherHolder(binding)
    }

    override fun getItemCount(): Int {
        return weatherResultResponseList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        val binding = holder.weatherRcvRowBinding


        val weatherResultResponse = weatherResultResponseList[position]

        binding.txtDate.text = weatherResultResponse.date
        binding.txtDay.text = weatherResultResponse.day

        when(weatherResultResponse.status) {
            "Rain" -> binding.ivStatusIcon.setImageResource(R.drawable.rainicon)
            "Snow" -> binding.ivStatusIcon.setImageResource(R.drawable.snowicon)
            "Clouds" -> binding.ivStatusIcon.setImageResource(R.drawable.cloudicon)
            "Clear" ->  binding.ivStatusIcon.setImageResource(R.drawable.sunicon)
            else -> binding.ivStatusIcon.setImageResource(R.drawable.sunicon)
        }
        //val iconUrl = weatherResultResponse.icon
        //Glide.with(context).load(iconUrl).override(40,40).into(binding.ivStatusIcon)

        binding.txtMinSeekbar.text = "${weatherResultResponse.min.split(".").first()} °C"
        binding.txtMaxSeekbar.text = "${weatherResultResponse.max.split(".").first()} °C"

        binding.degreeSeekBar.min = weatherResultResponse.min.toDouble().toInt()
        binding.degreeSeekBar.max = weatherResultResponse.max.toDouble().toInt()

        binding.degreeSeekBar.isEnabled = false
        binding.degreeSeekBar.thumb = null


    }
}