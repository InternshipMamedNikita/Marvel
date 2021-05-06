package ru.spb.iac.kotlin_mobile_template.activitities.marvel.presenter

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.CharactersObject
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.ItemCharacterModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.MarvelCharactersModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersView
import ru.spb.iac.kotlin_mobile_template.architecture.model.api.API
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api.ApiResponseHandler
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api.DefaultApiActions
import ru.spb.iac.kotlin_mobile_template.common.reciclerview.AutoLoadingRecyclerViewAdapter
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityRssFeedBinding

class MarvelCharactersPresenter (view: MarvelCharactersView,
                                 private val binding: ActivityRssFeedBinding
): AbstractPresenter<MarvelCharactersView>(view) {
    init{
        val defHand = DefaultApiActions(this.subscription,this.view)
        val handler = ApiResponseHandler(defHand)
        handler.prepareSubscribtion(API.getMarvelApi().getCharacters(), { response ->
            CharactersObject.characterDataWrapper = response.body()
            val data = MarvelCharactersModel(CharactersObject.characterDataWrapper?.data?.results?.let {
                ArrayList(
                    it
                )
            })
            binding.model = data
            binding.recycler.adapter = data.getCharacters()?.let { MarvelCharactersAdapter(it) }
        }).subscribe()
    }
    override fun onStart() {
        binding.searchingTitle.addTextChangedListener(object :TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.searchingTitle.text.isNullOrEmpty()) {
                    (binding.recycler.adapter as MarvelCharactersAdapter).setModel(CharactersObject.characterDataWrapper?.data?.results!!)
                } else {
                    (binding.recycler.adapter as MarvelCharactersAdapter).setModel((CharactersObject.characterDataWrapper?.data?.results!!).filter {
                        it.name?.contains(binding.searchingTitle.text.toString(), true)!!
                    })
                }
            }
        })
    }
    override fun onDestroyed() {
    }
    override fun startActivityForResult(intent: Intent?, responseCode: Int) {
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return this.view.actionBar?.onCreateOptionsMenu(menu) ?: false
    }
    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return this.view.actionBar?.onOptionsItemSelected(menuItem) ?: false
    }
}