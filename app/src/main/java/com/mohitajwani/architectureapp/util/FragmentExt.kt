
package com.mohitajwani.architectureapp.util

/**
 * Extension functions for Fragment.
 */

import androidx.fragment.app.Fragment
import com.mohitajwani.architectureapp.TodoApplication
import com.mohitajwani.architectureapp.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as TodoApplication).taskRepository
    return ViewModelFactory(repository)
}
