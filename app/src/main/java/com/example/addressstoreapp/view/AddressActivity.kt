package com.example.addressstoreapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.addressstoreapp.R
import com.example.addressstoreapp.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addressNavHost = supportFragmentManager.findFragmentById(R.id.addressNavHostFragment) as NavHostFragment
        val addressNavController = addressNavHost.navController

    }
}