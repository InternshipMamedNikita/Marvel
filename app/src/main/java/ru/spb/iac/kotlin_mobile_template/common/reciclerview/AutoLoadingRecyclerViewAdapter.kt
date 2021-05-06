package ru.spb.iac.kotlin_mobile_template.common.reciclerview

import androidx.recyclerview.widget.RecyclerView
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel.BindableAdapter

/**
 * Created by nixbyte on 20,Декабрь,2019
 */
abstract class AutoLoadingRecyclerViewAdapter<T>(var listElements: MutableList<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableAdapter<List<T>> {


    fun clear() {
        listElements.clear()
        notifyDataSetChanged()
    }

    override fun setModel(data: List<T>) {
       listElements.clear()
       listElements.addAll(data)
       notifyDataSetChanged()
    }

    fun addItem(item : T, position: Int = listElements.size - 1) {
        val lPosition = if (position >= listElements.size)
            listElements.size - 1
        else
            position
        listElements.add(lPosition, item)
        notifyItemInserted(lPosition)
    }

    fun addNewItems(items: List<T>) {
        listElements.addAll(items)
        notifyItemRangeInserted(listElements.size - 1, items.size)
    }

    fun removeItem(position: Int) {
        if (position < listElements.size) {
            listElements.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItems(): List<T> {
        return listElements
    }

    fun getItem(position: Int): T {
        return listElements[position]
    }

    override fun getItemCount(): Int {
        return listElements.size
    }
}