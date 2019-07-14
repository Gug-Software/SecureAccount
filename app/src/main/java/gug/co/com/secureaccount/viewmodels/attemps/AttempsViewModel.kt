package gug.co.com.secureaccount.viewmodels.attemps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gug.co.com.secureaccount.R
import gug.co.com.secureaccount.contract.attemps.IContractAttemps
import gug.co.com.secureaccount.data.domain.Attemp
import gug.co.com.secureaccount.utils.WorkStatus
import gug.co.com.secureaccount.utils.asDomainModel
import gug.co.com.secureaccount.viewmodels.BaseViewModel
import gug.co.com.secureaccountlib.util.Result
import kotlinx.coroutines.launch
import java.util.*

class AttempsViewModel(
    private val repository: IContractAttemps.Model
) : BaseViewModel(), IContractAttemps.ViewModel {

    private val _attemps = MutableLiveData<List<Attemp>>().apply { value = emptyList() }
    val attemps: LiveData<List<Attemp>> = _attemps

    private val _status = MutableLiveData<WorkStatus>()
    val status: LiveData<WorkStatus> = _status

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int> = _snackbarText

    override fun loadAttemps(userName: String) {

        uiScope.launch {
            try {
                _status.value = WorkStatus.WORKING
                val result = repository.getAttempsByUserName(userName)
                (result as? Result.Success)?.let {
                    _attemps.value = ArrayList(result.data.asDomainModel())
                }
                _status.value = WorkStatus.DONE
            } catch (e: Exception) {
                _snackbarText.value = R.string.msg_error
            }
        }

    }
}