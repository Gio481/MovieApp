package com.example.movieapp.util.extensions.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <DATA> Fragment.observer(liveData: LiveData<DATA?>, observer: (it: DATA) -> Unit) {
    liveData.observe(this.viewLifecycleOwner) { it?.let { it1 -> observer(it1) } }
}

fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}