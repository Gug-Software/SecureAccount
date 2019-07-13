package gug.co.com.secureaccount.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import gug.co.com.secureaccount.R
import gug.co.com.secureaccount.databinding.FragmentSplashBinding
import gug.co.com.secureaccount.viewmodels.splash.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    // Lazy inject ViewModel
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_splash, container, false
        )

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        defineObservers()

        return binding.root
    }

    private fun defineObservers() {

        viewModel.navToInit.observe(this, Observer { navigate ->
            if (navigate) {
                navigateToPpal()
                viewModel.onInitNavigatedDone()
            }
        })

    }

    private fun navigateToPpal() {
        this.findNavController().navigate(
            SplashFragmentDirections.actionSplashToAccountCreate()
        )
    }


}
