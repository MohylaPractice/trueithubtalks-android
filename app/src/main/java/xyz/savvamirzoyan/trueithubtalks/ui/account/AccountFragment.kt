package xyz.savvamirzoyan.trueithubtalks.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import xyz.savvamirzoyan.trueithubtalks.R
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
        setOnChangedUserNameListener()
        setOnChangedBioListener()

        setBioOnClickListener()

        return binding.root
    }

    private fun setBioOnClickListener() {
        binding.textViewBio.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToBioEditFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun setOnChangedNameListener() {
        viewModel.name.observe(viewLifecycleOwner) {
            binding.textViewName.text = it
        }
    }

    private fun setOnChangedUserNameListener() {
        viewModel.username.observe(viewLifecycleOwner) {
            binding.textViewUsername.text = it
        }
    }

    private fun setOnChangedBioListener() {
        viewModel.bio.observe(viewLifecycleOwner) {
            binding.textViewBio.text =
                if (it != "") it else getString(R.string.write_about_yourself_hint)
        }
    }
}