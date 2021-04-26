package com.example.applivrou.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BooksListRecyclerViewItemsDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) in 6..8) {
            view.setPadding(0, 0, 0, 20)
        }
    }
}