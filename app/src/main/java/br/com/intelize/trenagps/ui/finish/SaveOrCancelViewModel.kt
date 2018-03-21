package br.com.intelize.trenagps.ui.finish

import android.arch.lifecycle.ViewModel
import android.text.Editable
import br.com.intelize.trenagps.common.rx.SingleLiveEvent
import br.com.intelize.trenagps.data.MeasuresDatasource
import br.com.intelize.trenagps.model.MeasureType
import br.com.intelize.trenagps.model.MeasuredItem

class SaveOrCancelViewModel(private val datasource: MeasuresDatasource) : ViewModel() {

    val success: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        success.value = false
    }

    fun save(name: Editable?, value: CharSequence?, measureType: MeasureType.Type) {
        datasource.addMeasure(MeasuredItem(measureType, name.toString(), value.toString()))
        success.value = true
    }
}