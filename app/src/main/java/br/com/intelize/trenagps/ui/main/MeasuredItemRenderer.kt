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
import java.text.DateFormat

class MeasuredItemRenderer : Renderer<MeasuredItem>() {

	private lateinit var iconView: ImageView
	private lateinit var titleView: TextView
	private lateinit var distanceView: TextView
	private lateinit var measurementDate: TextView
	private lateinit var measurementTime: TextView

	override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
		return inflater.inflate(R.layout.list_item_measurement, parent, false)
	}

	override fun setUpView(rootView: View) {
		iconView = rootView.findViewById(measuredItemIcon)
		titleView = rootView.findViewById(measuredItemName)
		distanceView = rootView.findViewById(measuredItemDistance)
		measurementDate = rootView.findViewById(R.id.measurementDate)
		measurementTime = rootView.findViewById(R.id.measurementTime)
	}

	override fun render() {

		if (content.type == MeasureType.Type.STRAIGHT_LINE) {
			iconView.setImageResource(R.drawable.ic_route_single)
		} else if (content.type == MeasureType.Type.REALTIME) {
			iconView.setImageResource(R.drawable.ic_route_multi)
		}

		titleView.text = content.name
		distanceView.text = context.getString(R.string.x_meters, content.distance)
		measurementDate.text = DateFormat.getDateInstance(DateFormat.SHORT).format(content.registerDt)
		val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(content.registerDt)
		measurementTime.text = time.substring(0, time.length - 3)

	}

	override fun hookListeners(rootView: View?) {
	}
}