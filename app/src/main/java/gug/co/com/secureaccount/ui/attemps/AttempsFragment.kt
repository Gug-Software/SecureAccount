package gug.co.com.secureaccount.ui.attemps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import gug.co.com.secureaccount.R
import gug.co.com.secureaccount.databinding.FragmentAttempsBinding
import gug.co.com.secureaccount.ui.attemps.adapter.AttempsAdapter
import gug.co.com.secureaccount.viewmodels.attemps.AttempsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AttempsFragment : Fragment() {

    lateinit var binding: FragmentAttempsBinding
    private val viewModel by viewModel<AttempsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_attemps, container, false
        )

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        defineObservers()
        configureRecyclers()

        return binding.root

    }

    private fun configureRecyclers() {

        val adapter = AttempsAdapter()

        binding.moviesRecycler.adapter = adapter

        viewModel.attemps.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    override fun onStart() {
        super.onStart()
        viewModel.loadAttemps(AttempsFragmentArgs.fromBundle(arguments!!).userName)
    }

    private fun defineObservers() {


    }


}
