package xyz.savvamirzoyan.trueithubtalks.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("onCreateView() called")

        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        setLoginClickListener()
        setSignUpClickListener()
        setOnChangedNameListener()
        setOnChangedPasswordListener()

        // ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setTokenObserver()

        binding.editTextName.text.insert(0, viewModel.userName.value!!)
        binding.editTextPassword.text.insert(0, viewModel.userPassword.value!!)

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

    private fun setSignUpClickListener() {
        binding.textViewSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun setOnChangedNameListener() {
        binding.editTextName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updateNameFilled(s.toString())

                binding.buttonLogin.isEnabled = viewModel.isLoginButtonEnabled
                setAlphaByBoolean(binding.buttonLogin, viewModel.isLoginButtonEnabled)
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    private fun setOnChangedPasswordListener() {
        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updatePasswordFilled(s.toString())

                binding.buttonLogin.isEnabled = viewModel.isLoginButtonEnabled
                setAlphaByBoolean(binding.buttonLogin, viewModel.isLoginButtonEnabled)
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    private fun setAlphaByBoolean(view: View, value: Boolean) {
        view.alpha = 0.3F + 0.7F * value.toInt()
    }

    private fun setLoginClickListener() {
        binding.buttonLogin.setOnClickListener {

            viewModel.sendCredentials(
                binding.editTextName.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }

    private fun setTokenObserver() {
        viewModel.token.observe(viewLifecycleOwner, {
            // invalid credentials
            if (it == "") {
                binding.textViewInvalidCredentials.visibility = View.VISIBLE
            } else {
                binding.textViewInvalidCredentials.visibility = View.INVISIBLE


            }
        })
    }
}

private fun Boolean.toInt(): Int = if (this) 1 else 0
