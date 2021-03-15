package xyz.savvamirzoyan.trueithubtalks.ui.useraccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.R
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentUserAccountBinding
import xyz.savvamirzoyan.trueithubtalks.factory.UserAccountViewModelFactory
import xyz.savvamirzoyan.trueithubtalks.ui.MainActivity

class UserAccountFragment : Fragment() {

    private lateinit var binding: FragmentUserAccountBinding
    private lateinit var viewModel: UserAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("onCreateView() called")

        // Inflate the layout for this fragment
        binding = FragmentUserAccountBinding.inflate(inflater, container, false)

        // ViewModel
        val bundleArgs = UserAccountFragmentArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(
            this,
            UserAccountViewModelFactory(
                bundleArgs.chatId,
                context as MainActivity
            )
        ).get(UserAccountViewModel::class.java)

        setOnChangedPictureUrl()
        setOnChangedUsernameListener()

        return binding.root
    }

    private fun setOnChangedPictureUrl() {
        viewModel.pictureUrlLiveData.observe(viewLifecycleOwner) {
            Picasso.with(binding.imageViewUserPicture.context)
                .load(viewModel.pictureUrlLiveData.value)
                .placeholder(R.drawable.ic_account_circle)
                .into(binding.imageViewUserPicture)
        }
    }

    private fun setOnChangedUsernameListener() {
        viewModel.usernameLiveData.observe(viewLifecycleOwner) {
            binding.textViewUsername.text = it
        }
    }
}