package com.example.movieapp.presentation.ui.movies_collection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.LoaderAdapterLayoutBinding

class LoaderStateAdapter(private val action: (message: String) -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(LoaderAdapterLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), action)
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    class LoaderViewHolder(
        private val binding: LoaderAdapterLayoutBinding,
        private val action: (message: String) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(loadState: LoadState) {
            with(binding) {
                customLoader.isVisible = loadState is LoadState.Loading
                if (loadState is LoadState.Error) {
                    loadState.error.localizedMessage?.let { action.invoke(it) }
                }
            }
        }
    }

}