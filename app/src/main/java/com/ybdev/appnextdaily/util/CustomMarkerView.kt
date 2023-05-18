package com.ybdev.appnextdaily.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.ybdev.appnextdaily.R

@SuppressLint("ViewConstructor")
class CustomMarkerView(
    context: Context,
    layout: Int,
    private val dataToDisplay: MutableList<String>
) : MarkerView(context, layout) {

    private var txtViewData: TextView? = null
    private val totalWidth = resources.displayMetrics.widthPixels

    init {
        txtViewData = findViewById(R.id.custom_marker_view_text)
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        try {
            val xAxis = e?.x?.toInt() ?: 0
            txtViewData?.text = dataToDisplay[xAxis]
        } catch (_: IndexOutOfBoundsException) {
        }

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width / 2f), -height.toFloat())
    }

    override fun getOffsetForDrawingAtPoint(posX: Float, posY: Float): MPPointF {
        val supposedX = posX + width
        val mpPointF = MPPointF()

        mpPointF.x = when {
            supposedX > totalWidth -> -width.toFloat()
            posX - width < 0 -> 0f
            else -> 0f
        }

        mpPointF.y = if (posY > height)
            -height.toFloat()
        else
            0f
        return mpPointF
    }
}