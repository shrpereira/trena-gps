package br.com.intelize.trenagps.ui.modes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.intelize.trenagps.MainActivity
import br.com.intelize.trenagps.R
import br.com.intelize.trenagps.R.color.colorDetails
import br.com.intelize.trenagps.R.color.textInitial
import br.com.intelize.trenagps.R.drawable.shape_inner_circle
import br.com.intelize.trenagps.R.drawable.shape_inner_circle_selected
import br.com.intelize.trenagps.R.layout.fragment_straight_line
import kotlinx.android.synthetic.main.fragment_straight_line.*

class StraightLineFragment : Fragment() {

    private var measuring: Boolean = false

    companion object {
        fun newInstance(): StraightLineFragment {
            return StraightLineFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_straight_line, container, false)
    }

    override fun onStart() {
        super.onStart()

        configureProgressBar()
        configureAction()
    }

    private fun configureAction() {
        innerCircle.setOnClickListener({ if (measuring) setMeasuringOff() else setMeasuringOn() })
    }

    private fun configureProgressBar() {
        context?.let { ContextCompat.getColor(it, R.color.colorDetails) }?.let {
            actionProgressBar.indeterminateDrawable
                    .setColorFilter(it, android.graphics.PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun setMeasuringOn() {
        measuring = true
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