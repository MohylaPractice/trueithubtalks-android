package xyz.savvamirzoyan.trueithubtalks.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Binding
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        setOnChangedNameListener()
        setOnChangedPictureUrl()

        return binding.root
    }

    private fun setOnChangedPictureUrl() {
        viewModel.pictureUrl.observe(viewLifecycleOwner) {
            Picasso.with(binding.imageViewUserPicture.context)
                .load(viewModel.pictureUrl.value)
                .into(binding.imageViewUserPicture)
        }
    }

    private fun setOnChangedNameListener() {
        viewModel.name.observe(viewLifecycleOwner) {
            binding.textViewUsername.text = it
        }
    }
}