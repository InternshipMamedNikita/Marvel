package ru.spb.iac.kotlin_mobile_template.common.reciclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Created by nixbyte on 20,Декабрь,2019
 */

class SpacingItemDecoration(
    spanCount: Int,
    spacing: Int,
    includeEdge: Boolean,
    headerNum: Int) : ItemDecoration() {
    private val spanCount: Int = spanCount
    private val spacing: Int = spacing
    private val includeEdge: Boolean = includeEdge
    private val headerNum: Int = headerNum

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) - headerNum
        if (position >= 0) {
            val column = position % spanCount
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
                    outRect.top = spacing
                }
            }
        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }
    }
}