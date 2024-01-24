package com.example.addressstoreapp.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
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
import androidx.core.content.getSystemService
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.addressstoreapp.R
import com.example.addressstoreapp.databinding.FragmentLocationPermissionBinding
import com.example.addressstoreapp.manager.CitiesManager
import com.example.addressstoreapp.manager.WeatherManager
import com.example.addressstoreapp.room.UserDao
import com.example.addressstoreapp.room.UserDatabase
import com.example.addressstoreapp.room.UserEntity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale

class LocationPermissionFragment : Fragment(){
    private lateinit var permissionLauncher  : ActivityResultLauncher<String>
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private lateinit var userDb : UserDatabase
    private lateinit var userDao : UserDao


    private lateinit var binding : FragmentLocationPermissionBinding

    fun progressBarForGetLocation() {
        runBlocking {
            launch {
                if(userDao.getUser().isNotEmpty()){
                    binding.progressBar.visibility = View.INVISIBLE
                    val navHost = requireActivity().supportFragmentManager.findFragmentById(R.id.addressNavHostFragment) as NavHostFragment
                    navHost.navController.navigate(R.id.action_locationPermissionFragment_to_weatherFragment)
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLocationPermissionBinding.inflate(layoutInflater)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        binding.txtSelectCity.text = getString(R.string.selectCityText)
        userDb = Room.databaseBuilder(requireContext(),UserDatabase::class.java,"UserDatabase").allowMainThreadQueries().build()
        userDao = userDb.userDao()
        binding.progressBar.visibility = View.INVISIBLE
        val allCities = CitiesManager.getCities()

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


        var coarseLocationPermission = permissionLauncher()

        if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            coarseLocationPermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)

        } else {
            coarseLocationPermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)

            val navHost = requireActivity().supportFragmentManager.findFragmentById(R.id.addressNavHostFragment) as NavHostFragment

            navHost.navController.navigate(R.id.action_locationPermissionFragment_to_weatherFragment)
        }


    }
    private fun permissionLauncher() : ActivityResultLauncher<String> {
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {result ->

            if (result) {
                // izin verildi
                if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    fusedLocationClient.lastLocation.addOnSuccessListener {

                        if(it != null) {
                            //Burada GeoCoder için versiyon kontrolü yaptık yoksa fusedlocation ile ilgili değil.
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                val geoCoder = Geocoder(requireContext(), Locale.getDefault())
                                val addressCoder = geoCoder.getFromLocation(it.latitude,it.longitude,1,object : Geocoder.GeocodeListener {
                                    override fun onGeocode(addresses: MutableList<Address>) {
                                        for(address in addresses) {
                                            val cityName = address.adminArea
                                            val user = UserEntity("","",cityName)
                                            userDao.insertUser(user)
                                            progressBarForGetLocation()
                                        }
                                    }
                                })
                            } else {
                                val geoCoder = Geocoder(requireContext(), Locale.getDefault())
                                val address = geoCoder.getFromLocation(it.latitude,it.longitude,1)
                                val cityName = address!![0].adminArea
                                val user = UserEntity("","",cityName)
                                userDao.insertUser(user)
                                progressBarForGetLocation()
                            }

                        }
                    }

                }

            } else {
                if(userDao.getUser().isNotEmpty()){
                    progressBarForGetLocation()
                } else {
                    binding.saveButton.setOnClickListener {
                        val cityName = binding.textInputLayoutProvince.editText!!.text.toString()
                        val user = UserEntity("","",cityName)
                        userDao.insertUser(user)
                        progressBarForGetLocation()
                    }
                }
            }
        }
        return permissionLauncher

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


}