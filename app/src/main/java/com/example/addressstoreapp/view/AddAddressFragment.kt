package com.example.addressstoreapp.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.addressstoreapp.R
import com.example.addressstoreapp.manager.CitiesManager
import com.example.addressstoreapp.manager.WeatherManager
import com.example.addressstoreapp.databinding.FragmentAddAddressBinding
import com.example.addressstoreapp.room.UserDao
import com.example.addressstoreapp.room.UserDatabase
import com.example.addressstoreapp.room.UserEntity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar


class AddAddressFragment : Fragment() {
    private lateinit var binding : FragmentAddAddressBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAddressBinding.inflate(layoutInflater,container,false)


        val allCities = CitiesManager.getCities()
        val weatherManager = WeatherManager()
        val language = getString(R.string.language)

        binding.txtCityName.text = weatherManager.getWeather("Ankara","$language")[0].day


        //
        val citiesNameListForMenu =allCities.map {
            it.name
        }

        val provinceArrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,citiesNameListForMenu)
        binding.autoCompleteTvProvinces.setAdapter(provinceArrayAdapter)

        binding.autoCompleteTvProvinces.setOnItemClickListener { parent, view, position, id ->
            val selectedProvince = parent.getItemAtPosition(position)

            val selectedCity = allCities.first {
                it.name == selectedProvince
            }

            val districtNameList = CitiesManager.getDistrict(selectedCity.id).map {
                it.name
            }
            binding.textInputLayoutDistrict.editText!!.setText("")
            binding.textInputLayoutNeighbourhoods.editText!!.setText("")

            val districtArrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,districtNameList)
            binding.autoCompleteTvDistricts.setAdapter(districtArrayAdapter)

            binding.autoCompleteTvDistricts.showDropDown()

            binding.autoCompleteTvDistricts.setOnItemClickListener { parent, view, position, id ->

                val selectedDistrict = parent.getItemAtPosition(position).toString()
                val neighbourhoodNameListForMenu = CitiesManager.getNeighbourhood(selectedCity.id,selectedDistrict).map {
                    it.name
                }
                binding.textInputLayoutNeighbourhoods.editText!!.setText("")

                val neighbourhoodArrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,neighbourhoodNameListForMenu)
                binding.autoCompleteTvNeighbourhoods.setAdapter(neighbourhoodArrayAdapter)

                binding.autoCompleteTvNeighbourhoods.showDropDown()

            }
        }


        return binding.root
    }



}