package xyz.savvamirzoyan.trueithubtalks.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.R
import xyz.savvamirzoyan.trueithubtalks.interfaces.IAuthenticateActivity

class AuthenticateActivity : AppCompatActivity(), IAuthenticateActivity {

    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticate)

        Timber.i("onCreate() called")
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

    override fun showBackButton(value: Boolean) {
        this.supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    override fun onBackPressed() {
        val fragmentManager: FragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }

    override fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

fun Boolean.toInt(): Int = if (this) 1 else 0