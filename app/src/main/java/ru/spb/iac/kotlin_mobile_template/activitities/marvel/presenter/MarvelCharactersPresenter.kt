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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
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

    init {
        val defHand = DefaultApiActions(subscription, view)
        val handler = ApiResponseHandler(defHand)
        handler.prepareSubscribtion(API.getMarvelApi().getCharacters(), { response ->
            CharactersObject.characterDataWrapper = response.body()
            MarvelCharactersModel(CharactersObject.characterDataWrapper?.data?.results).let { model ->
                binding.model = model
                model.getCharacters()?.let { characters ->
                    binding.recycler.adapter = MarvelCharactersAdapter(ArrayList(characters))
                }
            }
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
        (binding.root.context as AppCompatActivity).menuInflater.inflate(R.menu.toolbar_menu, menu)

        (menu?.findItem(R.id.action_search)?.actionView as EditText).let { editText ->
            editText.hint = "Введите имя персонажа"
            binding.root.context.run {
                editText.setHintTextColor(resources.getColor(R.color.white_hint))
                editText.setTextColor(resources.getColor(R.color.white))
                editText.background = null

                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        CharactersObject.characterDataWrapper?.data?.results?.let { characters ->
                            (binding.recycler.adapter as MarvelCharactersAdapter).let { adapter ->
                                if (s.isNullOrEmpty())
                                    adapter.setModel(characters)
                                else
                                    adapter.setModel(characters.filter { character ->
                                        character.name?.contains(s.toString(), true)!!
                                    })
                            }
                        }
                    }
                })
            }
        }
        return this.view.actionBar?.onCreateOptionsMenu(menu) ?: false
    }
    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        binding.root.context.run {
            if (menuItem.itemId == R.id.profile) {
                startActivity(Intent(this, Profile::class.java))
            }
            else if (menuItem.itemId == R.id.exit) {
                (this as MarvelCharactersActivity).exit(null)
            }
        }
        return this.view.actionBar?.onOptionsItemSelected(menuItem) ?: false
    }
}