package xyz.savvamirzoyan.trueithubtalks.ui.signup

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentSignUpBinding
import xyz.savvamirzoyan.trueithubtalks.interfaces.IAuthenticateActivity
import xyz.savvamirzoyan.trueithubtalks.ui.toInt


class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("onCreateView() called")

        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        // Setting listeners and values
        setOnChangedNameListener()
        setOnChangedPasswordListener()
        setOnChangedPasswordCopyListener()
        setSignUpClickListener()

        setTokenObserver()

        binding.editTextName.text.insert(0, viewModel.userName)
        binding.editTextPassword.text.insert(0, viewModel.userPassword)
        binding.editTextPasswordCopy.text.insert(0, viewModel.userPasswordCopy)

        binding.buttonSignUp.isEnabled = viewModel.isSignUpButtonEnabled
        setAlphaByBoolean(binding.buttonSignUp, viewModel.isSignUpButtonEnabled)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.i("onCreate() called")
    }

    override fun onStart() {
        super.onStart()

        Timber.i("onStart() called")
    }

    override fun onResume() {
        super.onResume()

        Timber.i("onResume() called")
    }

    override fun onPause() {
        super.onPause()

        Timber.i("onPause() called")
    }

    override fun onStop() {
        super.onStop()

        Timber.i("onStop() called")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Timber.i("onDestroyView() called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.i("onDestroy() called")
    }

    override fun onDetach() {
        super.onDetach()

        Timber.i("onDetach() called")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Timber.i("onAttach() called")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.i("onViewCreated() called")
    }

    private fun setOnChangedNameListener() {
        binding.editTextName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updateNameFilled(s.toString())

                binding.buttonSignUp.isEnabled = viewModel.isSignUpButtonEnabled
                setAlphaByBoolean(binding.buttonSignUp, viewModel.isSignUpButtonEnabled)
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    private fun setOnChangedPasswordListener() {
        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updatePasswordFilled(s.toString())

                binding.buttonSignUp.isEnabled = viewModel.isSignUpButtonEnabled
                setAlphaByBoolean(binding.buttonSignUp, viewModel.isSignUpButtonEnabled)
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    private fun setOnChangedPasswordCopyListener() {
        binding.editTextPasswordCopy.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updatePasswordCopyFilled(s.toString())

                binding.buttonSignUp.isEnabled = viewModel.isSignUpButtonEnabled
                setAlphaByBoolean(binding.buttonSignUp, viewModel.isSignUpButtonEnabled)
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    private fun setSignUpClickListener() {
        binding.buttonSignUp.setOnClickListener {
            viewModel.createUser(
                binding.editTextName.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }

    private fun setAlphaByBoolean(view: View, value: Boolean) {
        view.alpha = 0.3F + 0.7F * value.toInt()
    }

    private fun setTokenObserver() {
        viewModel.token.observe(viewLifecycleOwner, {
            viewModel.saveToken(it)
            (activity as IAuthenticateActivity).moveToMainActivity()
        })
    }
}
