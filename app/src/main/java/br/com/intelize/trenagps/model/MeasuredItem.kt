package br.com.intelize.trenagps.model

data class MeasuredItem(
        val type: MeasureType.Type,
        val name: String,
        val distance: String)