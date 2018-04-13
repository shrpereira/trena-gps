package br.com.intelize.trenagps.ui.realtime

import android.arch.lifecycle.Observer
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.intelize.trenagps.R
import br.com.intelize.trenagps.R.layout.fragment_realtime
import br.com.intelize.trenagps.model.MeasureType
import br.com.intelize.trenagps.ui.main.MainActivity
import br.com.intelize.trenagps.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_realtime.*
import org.koin.android.architecture.ext.getViewModel
import org.koin.android.architecture.ext.viewModel

class RealtimeFragment : Fragment() {

    private var measuring: Boolean = false
    private lateinit var location: Location
    private val viewModel by viewModel<RealtimeViewModel>()

    companion object {
        fun newInstance(): RealtimeFragment {
            return RealtimeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(fragment_realtime, container, false)

        (activity as MainActivity).getViewModel<MainViewModel>().location.observe(this, Observer {
            it?.let {
                accuracyTextView.text = getString(R.string.accuracy_meters, String.format("%.2f", it.accuracy))
                if (measuring) {
                    viewModel.updateMeasuring(it)
                } else {
                    location = it
                }
            }
        })

        viewModel.distance.observe(this, Observer {
            it?.let {
                if (measuring) {
                    actionButtonText.text = String.format("%.2f", it)
                }
            }
        })

        viewModel.finalDistance.observe(this, Observer {
            it?.let {
                if (it > 0.0F) {
                    (activity as MainActivity).redirectToSaveScreen(it, MeasureType.Type.REALTIME.ordinal)
                } else {
                    Toast.makeText(context, getString(R.string.you_should_move_yourself), Toast.LENGTH_SHORT).show()
                }
            }
        })

        return view
    }

    override fun onStart() {
        super.onStart()

        configureAction()
    }

    private fun configureAction() {
        innerCircle.setOnClickListener {
            if (!measuring) setMeasuringOn() else setMeasuringOff()
        }
    }

    private fun setMeasuringOn() {
        measuring = true

        viewModel.startMeasuring(location)

        context?.let { actionButtonText.setTextColor(ContextCompat.getColor(it, R.color.colorDetails)) }
        actionProgressBar.visibility = View.VISIBLE
        innerCircle.background = context?.let { ContextCompat.getDrawable(it, R.drawable.shape_inner_circle_selected) }
//        (activity as MainActivity).hidePagerDots()
    }

    private fun setMeasuringOff() {
        measuring = false

        viewModel.finishMeasuring(location)

        actionButtonText.text = getString(R.string.begin)
        context?.let { actionButtonText.setTextColor(ContextCompat.getColor(it, R.color.textInitial)) }
        actionProgressBar.visibility = View.GONE
        innerCircle.background = context?.let { ContextCompat.getDrawable(it, R.drawable.shape_inner_circle) }
//        (activity as MainActivity).showPagerDots()
    }
}