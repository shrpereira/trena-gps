package br.com.intelize.trenagps.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.intelize.trenagps.R
import br.com.intelize.trenagps.R.id.*
import br.com.intelize.trenagps.model.MeasureType
import br.com.intelize.trenagps.model.MeasuredItem
import com.pedrogomez.renderers.Renderer

class MeasuredItemRenderer : Renderer<MeasuredItem>() {

    lateinit var iconView: ImageView
    lateinit var titleView: TextView
    lateinit var distanceView: TextView

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
        return inflater.inflate(R.layout.list_item_measurement, parent, false)
    }

    override fun setUpView(rootView: View) {
        iconView = rootView.findViewById(measuredItemIcon)
        titleView = rootView.findViewById(measuredItemName)
        distanceView = rootView.findViewById(measuredItemDistance)
    }

    override fun render() {

        if (content.type == MeasureType.Type.STRAIGHT_LINE) {
            iconView.setImageResource(R.drawable.ic_route_single)
        } else if (content.type == MeasureType.Type.REALTIME) {
            iconView.setImageResource(R.drawable.ic_route_multi)
        }

        titleView.text = content.name
        distanceView.text = content.distance
    }

    override fun hookListeners(rootView: View?) {
    }
}