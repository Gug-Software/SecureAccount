package gug.co.com.secureaccount.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import gug.co.com.secureaccount.R
import gug.co.com.secureaccount.data.domain.Attemp

@BindingAdapter("resultValidation")
fun TextView.resultValidation(attemp: Attemp) {
    when (attemp.result) {
        true -> {
            setText(context.resources.getString(R.string.text_success))
            setTextColor(context.resources.getColor(R.color.primaryColor))
        }
        else -> {
            setText(context.resources.getString(R.string.text_failure))
            setTextColor(context.resources.getColor(R.color.secondaryColor))
        }
    }
}

@BindingAdapter("showFromStatus")
fun showFromApiStatus(view: View, status: WorkStatus?) {
    when (status) {
        WorkStatus.WORKING -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("hideFromStatus")
fun hideFromApiStatus(view: View, status: WorkStatus?) {
    when (status) {
        WorkStatus.WORKING -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}