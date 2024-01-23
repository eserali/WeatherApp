package com.example.addressstoreapp.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.addressstoreapp.R
import com.example.addressstoreapp.databinding.FragmentWelcomeBinding
import com.example.addressstoreapp.enum.SettingsName
import com.example.addressstoreapp.manager.CitiesManager
import com.example.addressstoreapp.manager.SettingsManager
import java.util.Calendar


class WelcomeFragment : Fragment() {
    private lateinit var binding : FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentWelcomeBinding.inflate(layoutInflater,container,false)
        val settingsManager = SettingsManager(requireContext())
        val settings =   settingsManager.getOrAdd(SettingsName.WelcomeFragmentTimeZone)
        val currentHour = Calendar.HOUR// gece 12 sabah 7 arası iyi geceler 7 ile 6 arası iyi günler 6 12 arası iyi akşamlar

        if (currentHour <= settings.value1!!.toInt()) {
            binding.txtWelcome.setText(R.string.welcomefragment_message_text_good_night)
        }else if (currentHour in settings.value1.toInt() + 1..settings.value2!!.toInt()) {
            binding.txtWelcome.setText(R.string.welcomefragment_message_text_good_Day)

        } else if(currentHour in settings.value2.toInt() +1 ..settings.value3!!.toInt()) {
            binding.txtWelcome.setText(R.string.welcomefragment_message_text_good_evening)
        }

        object : CountDownTimer(2000,1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                CitiesManager.getCities()
                val navHost = requireActivity().supportFragmentManager.findFragmentById(R.id.addressNavHostFragment) as NavHostFragment

                navHost.navController.navigate(R.id.action_welcomeFragment_to_locationPermissionFragment)
            }

        }.start()

        return binding.root
    }



}