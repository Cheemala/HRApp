package com.cheemala.hrapp.application.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

class AppUtils {
    companion object {
        fun showSnackBar(layout: View, msg: String, duration: Int) = Snackbar.make(
            layout, msg,
            duration
        ).show()
    }
}