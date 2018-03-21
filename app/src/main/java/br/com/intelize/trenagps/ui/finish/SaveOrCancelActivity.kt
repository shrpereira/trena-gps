package br.com.intelize.trenagps.ui.finish

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import br.com.intelize.trenagps.R.layout.activity_save_or_cancel
import br.com.intelize.trenagps.model.MeasureType
import br.com.intelize.trenagps.model.MeasureType.Companion.MEASURE_TYPE_EXTRA
import br.com.intelize.trenagps.ui.base.BaseActivity
import br.com.intelize.trenagps.ui.main.MainActivity.Companion.MEASURED_VALUE_EXTRA
import kotlinx.android.synthetic.main.activity_save_or_cancel.*
import org.koin.android.architecture.ext.viewModel

class SaveOrCancelActivity : BaseActivity() {

    private val viewModel by viewModel<SaveOrCancelViewModel>()

    private val measureType by lazy {
        MeasureType.Type.values()[intent.getIntExtra(MEASURE_TYPE_EXTRA, 0)]
    }

    private val measuredInputValue by lazy {
        intent.getDoubleExtra(MEASURED_VALUE_EXTRA, 0.0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_save_or_cancel)

        configureActions()
        configureUi()

        viewModel.success.observe(this, Observer {
            it?.let {
                if (it) {
                    setResult(Activity.RESULT_OK)
                    onBackPressed()
                }
            }
        })
    }

    private fun configureUi() {
        measuredValue.text = String.format("%.2f", measuredInputValue)
    }

    private fun configureActions() {
        saveButton.setOnClickListener { viewModel.save(measureName.text, measuredValue.text, measureType) }
        configureDiscardButtonAction()
    }

    private fun configureDiscardButtonAction() {
        discardButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
        finish()
    }
}
