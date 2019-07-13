package gug.co.com.secureaccount.viewmodels.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gug.co.com.secureaccount.viewmodels.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel
    : BaseViewModel() {

    private val _navToInit = MutableLiveData<Boolean>()
    val navToInit: LiveData<Boolean> = _navToInit

    init {
        uiScope.launch {
            delay(2_000)
            _navToInit.value = true
        }

    }

    fun onInitNavigatedDone() {
        _navToInit.value = false
    }

}