package com.app.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.app.presentation.R
import com.app.presentation.databinding.ActivityMainBinding
import com.app.presentation.viewModels.WeatherViewModel
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.getViewModel


class ApplicationActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = getViewModel()
        window.statusBarColor = applicationContext.getColor(R.color.transparent)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        toolbar= binding.appBarMain.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
//        toolbar.setNavigationIcon(R.drawable.cloudy_main)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        updateTheme()

//        weatherViewModel.newTheme.observe(this, Observer {
//            try {
//                findViewById<LinearLayoutCompat>(R.id.top_).background = applicationContext!!.getDrawable(weatherViewModel.newTheme.value!!)
//                findViewById<LinearLayoutCompat>(R.id.infoLayouts).background = applicationContext!!.getDrawable(weatherViewModel.newColor.value!!)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        })
//
    }

//    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
//        when (buttonView.id) {
//            R.id.switch_compat -> {
//                when (isChecked) {
//                    true -> {
//                        updateTheme()
//                        weatherViewModel.themeStateLiveData.postValue(AppTheme.SEA)
//                    }
//                    else -> {
//                        updateTheme()
//                        weatherViewModel.themeStateLiveData.postValue(AppTheme.FOREST)
//                    }
//                }
//            }
//        }
//        weatherViewModel.updateTheme()
//
//    }

    private fun updateTheme() {
        try {
            findViewById<ConstraintLayout>(R.id.bottom_menu_container).background = applicationContext!!.getDrawable(weatherViewModel.newColor.value!!)
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {}
}