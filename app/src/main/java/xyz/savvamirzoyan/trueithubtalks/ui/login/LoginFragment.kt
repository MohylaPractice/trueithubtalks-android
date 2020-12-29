package xyz.savvamirzoyan.trueithubtalks.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("onCreateView() called")

        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

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
}