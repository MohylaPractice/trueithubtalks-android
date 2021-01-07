package xyz.savvamirzoyan.trueithubtalks.ui.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.R
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentBioEditBinding

class BioEditFragment : Fragment() {

    private lateinit var binding: FragmentBioEditBinding
    private lateinit var viewModel: BioEditViewModel
    private lateinit var checkMenuItem: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        binding = FragmentBioEditBinding.inflate(inflater, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this).get(BioEditViewModel::class.java)

        setOnChangedBioEditTextListener()

        return binding.root
    }

    private fun setOnChangedBioEditTextListener() {
        binding.editTextBio.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkMenuItem.isEnabled = s.toString().length <= viewModel.maxBioLength
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        Timber.i("onCreateOptionsMenu() called")

        inflater.inflate(R.menu.bio_edit_menu, menu)
        checkMenuItem = menu.findItem(R.id.menuItemCheck)
    }
}