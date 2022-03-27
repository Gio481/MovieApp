package com.example.movieapp.util.extensions.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.movieapp.R

fun FragmentManager.transaction(fragment: Fragment) {
    this.beginTransaction()
        .replace(R.id.fragmentContainerView, fragment)
        .commit()
}

fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}