package com.example.movieapp.presentation.ui.movies_collection.adapter

import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnScrollListener(
    private val manager: GridLayoutManager,
    private val pageSize: Int,
    private val action: () -> Unit,
) :
    RecyclerView.OnScrollListener() {

    private var isScrolling = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lastItem =
            manager.findFirstVisibleItemPosition() + manager.childCount >= manager.itemCount
        val firstVisibleItemPosition =
            manager.findFirstVisibleItemPosition() >= FIRST_VISIBLE_ITEM_POSITION
        val moreThanShowedItems = manager.itemCount >= pageSize
        if (firstVisibleItemPosition && isScrolling && lastItem && moreThanShowedItems) {
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

    companion object {
        private const val FIRST_VISIBLE_ITEM_POSITION = 0
    }
}