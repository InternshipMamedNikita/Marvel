package ru.spb.iac.kotlin_mobile_template.activitities.marvel.presenter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.ItemCharacterModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.FullMarvelCharactersInfoActivity
import ru.spb.iac.kotlin_mobile_template.common.reciclerview.AutoLoadingRecyclerViewAdapter
import ru.spb.iac.kotlin_mobile_template.databinding.RssItemBinding

class MarvelCharactersAdapter (publications: MutableList<Character>): AutoLoadingRecyclerViewAdapter<Character>(publications) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RssItemBinding = DataBindingUtil.inflate(inflater, R.layout.rss_item, parent, false)
        return ItemCharacterViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemCharacterViewHolder
        itemHolder.binding.root.setOnClickListener {
            val intent = Intent(itemHolder.binding.root.context, FullMarvelCharactersInfoActivity::class.java)
            intent.putExtra("character", listElements[position])
            itemHolder.binding.root.context.startActivity(intent)
        }

        itemHolder.binding.model = ItemCharacterModel(listElements[position].name,
            listElements[position].description,
            listElements[position].thumbnail?.path + "/landscape_medium.jpg")
    }
    class ItemCharacterViewHolder(val binding: RssItemBinding): RecyclerView.ViewHolder(binding.root)
}