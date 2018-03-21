package br.com.intelize.trenagps.ui.straightLine

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
import br.com.intelize.trenagps.R.color.colorDetails
import br.com.intelize.trenagps.R.color.textInitial
import br.com.intelize.trenagps.R.drawable.shape_inner_circle
import br.com.intelize.trenagps.R.drawable.shape_inner_circle_selected
import br.com.intelize.trenagps.R.layout.fragment_straight_line
import br.com.intelize.trenagps.ui.main.MainActivity
import br.com.intelize.trenagps.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_straight_line.*
import org.koin.android.architecture.ext.getViewModel
import org.koin.android.architecture.ext.viewModel

class StraightLineFragment : Fragment() {

    private var measuring: Boolean = false
    private lateinit var location: Location
    private val viewModel by viewModel<StraightLineViewModel>()

    companion object {
        fun newInstance(): StraightLineFragment {
            return StraightLineFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragment_straight_line, container, false)

        (activity as MainActivity).getViewModel<MainViewModel>().location.observe(this, Observer {
            it?.let {
                location = it
            }
        })

        viewModel.distance.observe(this, Observer {
            it?.let {
                if (it > 0.0) {
                    setMeasuringOff()
                    (activity as MainActivity).redirectToSaveScreen(it)
                } else {
                    setMeasuringOff()
                    Toast.makeText(context, getString(R.string.you_should_move_yourself), Toast.LENGTH_SHORT).show()
                }
            }
        })

        return view
    }

    override fun onStart() {
        super.onStart()

        configureProgressBar()
        configureAction()
    }

    private fun configureAction() {
        innerCircle.setOnClickListener({
            if (measuring) viewModel.finishMeasuring(location) else setMeasuringOn()
        })
    }

    private fun configureProgressBar() {
        context?.let { ContextCompat.getColor(it, R.color.colorDetails) }?.let {
            actionProgressBar.indeterminateDrawable
                    .setColorFilter(it, android.graphics.PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun setMeasuringOn() {
        measuring = true

        viewModel.startMeasuring(location)

        actionButtonText.text = getString(R.string.stop)
        context?.let { actionButtonText.setTextColor(ContextCompat.getColor(it, colorDetails)) }
        actionProgressBar.visibility = View.VISIBLE
        innerCircle.background = context?.let { ContextCompat.getDrawable(it, shape_inner_circle_selected) }
        (activity as MainActivity).hidePagerDots()
    }

    private fun setMeasuringOff() {
        measuring = false

        actionButtonText.text = getString(R.string.begin)
        context?.let { actionButtonText.setTextColor(ContextCompat.getColor(it, textInitial)) }
        actionProgressBar.visibility = View.GONE
        innerCircle.background = context?.let { ContextCompat.getDrawable(it, shape_inner_circle) }
        (activity as MainActivity).showPagerDots()
    }
}