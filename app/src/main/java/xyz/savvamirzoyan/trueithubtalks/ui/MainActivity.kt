package xyz.savvamirzoyan.trueithubtalks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.R

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.i("onCreate() called")

        drawerLayout = findViewById(R.id.mainDrawerLayout)

        val navController = this.findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(
            findViewById<NavigationView>(R.id.navView),
            navController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.main_nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume() Called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause() Called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop() Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy() Called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart() Called")
    }
}