package gug.co.com.secureaccount.viewmodels.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gug.co.com.secureaccount.R
import gug.co.com.secureaccount.contract.account.IContractAccount
import gug.co.com.secureaccount.utils.WorkStatus
import gug.co.com.secureaccount.viewmodels.BaseViewModel
import gug.co.com.secureaccountlib.util.Result
import kotlinx.coroutines.launch

class AccountViewModel(
    private val repository: IContractAccount.Model
) : BaseViewModel(), IContractAccount.ViewModel {

    private val _navToAttemps = MutableLiveData<String>()
    val navToAttemps: LiveData<String> = _navToAttemps

    private val _showMsgErrors = MutableLiveData<Boolean>()
    val showMsgErrors: LiveData<Boolean> = _showMsgErrors

    private val _status = MutableLiveData<WorkStatus>()
    val status: LiveData<WorkStatus> = _status

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int> = _snackbarText

    override fun createAccount(
        userName: String,
        password: String
    ) {

        uiScope.launch {
            try {
                _status.value = WorkStatus.WORKING
                val result = repository.createAccount(userName, password)
                (result as? Result.Success)?.let {
                    when (result.data) {
                        true -> {
                            _snackbarText.value = R.string.msg_createOK
                            _showMsgErrors.value = false
                        }
                        else -> {
                            _snackbarText.value = R.string.msg_createError
                            _showMsgErrors.value = true
                        }
                    }
                }
                _status.value = WorkStatus.DONE
            } catch (e: Exception) {
                _snackbarText.value = R.string.msg_error
            }
        }

    }

    override fun validateAccount(
        userName: String,
        password: String
    ) {

        uiScope.launch {
            try {
                _status.value = WorkStatus.WORKING
                val result = repository.validateAccount(userName, password)
                (result as? Result.Success)?.let {
                    when (result.data) {
                        true -> {
                            _snackbarText.value = R.string.msg_validateOK
                            _showMsgErrors.value = false
                            _navToAttemps.value = userName
                        }
                        else -> {
                            _snackbarText.value = R.string.msg_validateError
                            _showMsgErrors.value = true
                        }
                    }
                }
                _status.value = WorkStatus.DONE
            } catch (e: Exception) {
                _snackbarText.value = R.string.msg_error
            }
        }
    }

    fun onNavigateAttempsDone() {
        _navToAttemps.value = null
    }

    fun onSnackBarDone() {
        _snackbarText.value = 0
    }
}