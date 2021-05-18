package ru.spb.iac.kotlin_mobile_template.activitities.marvel.presenter

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.view.Profile
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.CharactersObject
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.MarvelCharactersModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersActivity
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersView
import ru.spb.iac.kotlin_mobile_template.architecture.model.api.API
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api.ApiResponseHandler
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api.DefaultApiActions
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityRssFeedBinding
import ru.spb.iac.kotlin_mobile_template.services.App


class MarvelCharactersPresenter (view: MarvelCharactersView,
                                 private val binding: ActivityRssFeedBinding
): AbstractPresenter<MarvelCharactersView>(view) {

    val context = binding.root.context

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

    }
    override fun onDestroyed() {
    }
    override fun startActivityForResult(intent: Intent?, responseCode: Int) {
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        (context as AppCompatActivity).menuInflater.inflate(R.menu.toolbar_menu, menu)

        var search = menu!!.findItem(R.id.action_search)!!.actionView as EditText
        search.hint = "Введите имя персонажа"
        search.setHintTextColor(context.resources.getColor(R.color.white_hint))
        search.setTextColor(context.resources.getColor(R.color.white))
        search.background = null
        search.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (search.text.isNullOrEmpty()) {
                    (binding.recycler.adapter as MarvelCharactersAdapter).setModel(CharactersObject.characterDataWrapper?.data?.results!!)
                } else {
                    (binding.recycler.adapter as MarvelCharactersAdapter).setModel((CharactersObject.characterDataWrapper?.data?.results!!).filter {
                        it.name?.contains(search.text.toString(), true)!!
                    })
                }
            }
        })

//        menu.getItem(R.id.profile).setOnMenuItemClickListener {
//             TODO("Sjsalfjlskdjfasjdfsadflsadjf")
//        }

        return this.view.actionBar?.onCreateOptionsMenu(menu) ?: false
    }
    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        val id = menuItem.itemId
        if (id == R.id.profile)
        {
            context.startActivity(Intent(context, Profile::class.java))
           //переход на Profile::class.java
        }
        if (id == R.id.exit)
        {
//            (context as AppCompatActivity).onBackPressed()
            (context as MarvelCharactersActivity).exit(null)
        }
        return this.view.actionBar?.onOptionsItemSelected(menuItem) ?: false
    }
}