package br.com.intelize.trenagps.data

import br.com.intelize.trenagps.TrenaApplication
import br.com.intelize.trenagps.extensions.apply
import br.com.intelize.trenagps.model.MeasuredItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MeasuresDatasource {

    companion object {
        private const val MEASURES_PREFERENCES = "measurePreferences"
        private const val MEASURES_LIST = "measuresList"
    }

    fun getMeasures(): List<MeasuredItem> {
        val measures = TrenaApplication.getApplication().getSharedPreferences(Companion.MEASURES_PREFERENCES).getString(MEASURES_LIST, "")

        if (measures.isEmpty()) return ArrayList()

        val listType = object : TypeToken<ArrayList<MeasuredItem>>() {}.type
        return Gson().fromJson(measures, listType)
    }

    fun addMeasure(measure: MeasuredItem) {
        val measuresList = getMeasures() as ArrayList<MeasuredItem>

        measuresList.add(measure)

        TrenaApplication.getApplication().getSharedPreferences(Companion.MEASURES_PREFERENCES).apply {
            putString(MEASURES_LIST, Gson().toJson(measuresList))
        }
    }
}