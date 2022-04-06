package com.example.movieapp.presentation.ui.movies_collection.adapter

import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnScrollListener(private val action: () -> Unit) : RecyclerView.OnScrollListener() {

    private var isScrolling = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val recyclerLayoutManager = recyclerView.layoutManager as GridLayoutManager
        val lastItem =
            recyclerLayoutManager.findFirstVisibleItemPosition() + recyclerLayoutManager.childCount >= recyclerLayoutManager.itemCount
        val firstVisibleItemPosition = recyclerLayoutManager.findFirstVisibleItemPosition() >= 0
        val moreThanShowedItems = recyclerLayoutManager.itemCount >= 20
        if (firstVisibleItemPosition && isScrolling && lastItem && firstVisibleItemPosition && moreThanShowedItems) {
            if (dy > 0) {
                action()
            }
            isScrolling = false
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true
        }
    }
}