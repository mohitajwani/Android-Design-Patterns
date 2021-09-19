package com.mohitajwani.architectureapp.tasks

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import androidx.test.core.app.ActivityScenario
import com.mohitajwani.architectureapp.R

fun <T : Activity> ActivityScenario<T>.getToolbarNavigationContentDescription()
    : String {
    var description = ""
    onActivity {
        description =
            it.findViewById<Toolbar>(R.id.toolbar).navigationContentDescription as String
    }
    return description
}
