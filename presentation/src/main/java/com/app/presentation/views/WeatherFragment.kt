package com.app.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.app.presentation.R
import com.app.presentation.databinding.FragmentWeatherBinding
import com.app.presentation.theme.AppTheme
import com.app.presentation.viewModels.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding: FragmentWeatherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        dataBinding.weatherViewModel = weatherViewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

        weatherViewModel.getWeatherUpdate(context!!)
        weatherViewModel.getWeatherForeCast(context)

        weatherViewModel.weatherUpdateTypeLiveData.observe(viewLifecycleOwner, Observer {
            weatherViewModel.updateTheme()
        })

        weatherViewModel.themeStateLiveData.observe(viewLifecycleOwner, Observer {
            weatherViewModel.updateTheme()
        })
        
        weatherViewModel.newTheme.observe(viewLifecycleOwner, Observer {
            try {
                view.findViewById<LinearLayoutCompat>(R.id.top_).background = context!!.getDrawable(weatherViewModel.newTheme.value!!)
                view.findViewById<LinearLayoutCompat>(R.id.infoLayouts).background = context!!.getDrawable(weatherViewModel.newColor.value!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

    }

    fun updatetheme(theme: AppTheme) {
        weatherViewModel.themeStateLiveData.postValue(theme)
    }

}