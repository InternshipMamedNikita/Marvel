package ru.spb.iac.kotlin_mobile_template.activitities.main.presenter

import android.content.Context
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.ViewModelDataBindingComponent
import ru.spb.iac.kotlin_mobile_template.common.reciclerview.AutoLoadingRecyclerViewAdapter
import ru.spb.iac.kotlin_mobile_template.databinding.ListItemBinding
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 * Created by nixbyte on 13,Февраль,2020
 */

class UserListAdapter(listElements: MutableList<UserEntity>)
    : AutoLoadingRecyclerViewAdapter<UserEntity>(listElements) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(App.context)
            ,R.layout.list_item
            ,parent
            ,false
            ,ViewModelDataBindingComponent()))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemViewHolder
        itemHolder.binding.text = listElements[position].name
        itemHolder.binding.model = listElements[position]
    }

    class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}