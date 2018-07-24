package br.com.intelize.trenagps.ui.main

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.LEFT
import android.support.v7.widget.helper.ItemTouchHelper.RIGHT

class SwipeController(private val onItemSwiped: (RecyclerView.ViewHolder) -> Unit) : ItemTouchHelper.Callback() {

	private lateinit var recyclerView: RecyclerView

	override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
		return ItemTouchHelper.Callback.makeMovementFlags(0, LEFT or RIGHT)
	}

	override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
		this.recyclerView = recyclerView
		return false
	}

	override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
		onItemSwiped(viewHolder)
	}
}