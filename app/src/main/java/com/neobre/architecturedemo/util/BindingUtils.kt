package com.neobre.architecturedemo.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.neobre.architecturedemo.R

@BindingAdapter("android:text")
fun setCountText(view: TextView, count: Int) {
    if (count > 3) {
        view.context.showRetryLimitReached {
            view.findNavController().popBackStack()
        }
    } else {
        view.text = view.context.getString(R.string.count_text, count)
    }
}
