package gug.co.com.secureaccount.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import gug.co.com.secureaccount.R
import gug.co.com.secureaccount.contract.account.IContractAccount
import gug.co.com.secureaccount.databinding.FragmentAccountBinding
import gug.co.com.secureaccount.viewmodels.account.AccountViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import org.koin.android.viewmodel.ext.android.viewModel

class AccountFragment
    : Fragment(), IContractAccount.View {

    lateinit var binding: FragmentAccountBinding
    // Lazy inject ViewModel
    private val viewModel by viewModel<AccountViewModel>()

    var isCreating = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_account, container, false
        )

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        defineObservers()
        defineClickListeners()

        return binding.root

    }

    private fun defineClickListeners() {

        binding.enterButton.setOnClickListener {

            var isEmptyUserName = false
            var isEmptyPassword = false

            if (username_text.text.toString().isEmpty()) {
                binding.usernameInputLayout.error = getString(R.string.msg_noEmpty)
                isEmptyUserName = true
            } else {
                binding.usernameInputLayout.error = null
            }
            if (password_text.text.toString().isEmpty()) {
                binding.passwordInputLayout.error = getString(R.string.msg_noEmpty)
                isEmptyPassword = true
            } else {
                binding.passwordInputLayout.error = null
            }

            if (!isEmptyUserName && !isEmptyPassword) {
                if (isCreating) {
                    viewModel.createAccount(
                        binding.usernameText.text.toString(),
                        binding.passwordText.text.toString()
                    )
                } else {
                    viewModel.validateAccount(
                        binding.usernameText.text.toString(),
                        binding.passwordText.text.toString()
                    )
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        isCreating = AccountFragmentArgs.fromBundle(arguments!!).isCreating
        when (isCreating) {
            true -> binding.enterButton.text = getString(R.string.button_createAccount)
            else -> binding.enterButton.text = getString(R.string.button_validateAccount)
        }
    }

    private fun defineObservers() {

        viewModel.navToAttemps.observe(this, Observer { navigate ->
            if (navigate) {
                navigateToAttempsList(binding.usernameText.text.toString())
                viewModel.onNavigateAttempsDone()
            }
        })

        viewModel.showMsgErrors.observe(this, Observer { showErrors ->
            if (showErrors) {
                binding.usernameInputLayout.error = getString(R.string.msg_userNameInstructions)
                binding.passwordInputLayout.error = getString(R.string.msg_passwordInstructions)
            } else {
                binding.usernameInputLayout.error = null
                binding.passwordInputLayout.error = null
                binding.usernameText.text = null
                binding.passwordText.text = null
            }
        })

        viewModel.snackbarMessage.observe(this, Observer {
            Snackbar.make(binding.coordinator, getString(it), Snackbar.LENGTH_LONG).show()
        })
    }

    override fun navigateToAttempsList(userName: String) {

        this.findNavController().navigate(
            AccountFragmentDirections.actionGlobalAttemps(userName)
        )

    }


}
