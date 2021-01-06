package xyz.savvamirzoyan.trueithubtalks.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    private val apiSourceLink = "https://github.com/savvasenok/trueithubtalks-backend"
    private val appSourceLink = "https://github.com/savvasenok/trueithubtalks-android"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentAboutBinding.inflate(inflater, container, false)

        val apiIntent = Intent(Intent.ACTION_VIEW, Uri.parse(apiSourceLink))
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(appSourceLink))

        binding.buttonApiSource.setOnClickListener { startActivity(apiIntent) }
        binding.buttonAppSource.setOnClickListener { startActivity(appIntent) }

        return binding.root
    }
}