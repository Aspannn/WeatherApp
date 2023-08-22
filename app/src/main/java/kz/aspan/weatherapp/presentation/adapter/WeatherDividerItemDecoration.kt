package kz.aspan.weatherapp.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class WeatherDividerItemDecoration(
    private val outerTop: Int,
    private val outerBottom: Int,
    private val inner: Int,
    private val outerHorizontal: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val viewHolder = parent.getChildViewHolder(view)
        val adapter = parent.adapter ?: return
        val currentPosition = parent.getChildAdapterPosition(view)
            .takeIf { it != RecyclerView.NO_POSITION } ?: viewHolder.oldPosition

        val isPrevTargetView = adapter.isPrevTargetView(currentPosition)
        val isNextTargetView = adapter.isNextTargetView(currentPosition)

        with(outRect) {
            top = if (isPrevTargetView) inner / 2 else outerTop
            bottom = if (isNextTargetView) inner / 2 else outerBottom
            left = outerHorizontal
            right = outerHorizontal
        }
    }

    private fun RecyclerView.Adapter<*>.isPrevTargetView(currentPosition: Int): Boolean {
        return currentPosition != 0
    }


    private fun RecyclerView.Adapter<*>.isNextTargetView(currentPosition: Int): Boolean {
        val lastIndex = itemCount - 1
        return currentPosition != lastIndex
    }

}
