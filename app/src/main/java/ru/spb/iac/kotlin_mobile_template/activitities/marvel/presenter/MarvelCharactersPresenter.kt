package ru.spb.iac.kotlin_mobile_template.activitities.marvel.presenter

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.appdatabase.DBConnection
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.view.Profile
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.CharactersObject
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.MarvelCharactersModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersActivity
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersView
import ru.spb.iac.kotlin_mobile_template.architecture.model.api.API
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityRssFeedBinding
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress
import kotlin.concurrent.thread


class MarvelCharactersPresenter (view: MarvelCharactersView,
                                 private val binding: ActivityRssFeedBinding
): AbstractPresenter<MarvelCharactersView>(view) {

    init {

        binding.swipeRefresh.setOnRefreshListener {
            load()
            binding.swipeRefresh.isRefreshing = false
        }
            load()
    }
    private fun load() {
        thread {
            if (isOnline()) {
                Log.e(TAG, "init: isOnline = true")
                loadCharactersFromServer()
            }
            else {
                Log.e(TAG, "init: isOnline = false")
                loadCharactersFromDatabase()
            }
        }
    }
    private fun setCharacters(characters: MutableList<Character>?) {
        MarvelCharactersModel(characters).let { model ->
            binding.model = model
            model.getCharacters()?.let { characters ->
                binding.recycler.adapter = MarvelCharactersAdapter(ArrayList(characters))
            }
        }
    }

    private fun loadCharactersFromServer() {
        API.getMarvelApi().getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { response ->
                CharactersObject.characterDataWrapper = response.body()
                setCharacters(CharactersObject.characterDataWrapper?.data?.results)
                saveCharactersToDatabase(CharactersObject.characterDataWrapper?.data?.results)
            }.subscribe()
    }

    private fun loadCharactersFromDatabase() {
        DBConnection.database.getMarverCharactersDao().getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { characterRelations ->
                val characters: MutableList<Character> = ArrayList()

                Log.e(TAG, "doOnSuccess: $characterRelations")
                if (characterRelations.isEmpty()) {
                    Toast.makeText(binding.root.context, "Необходимо подключение к интернету для загрузки персонажей", Toast.LENGTH_SHORT).show()
                    return@doOnSuccess
                }

                characterRelations.forEach { item ->
                    Character(item.character?.id, item.character?.name, item.character?.description,
                        item.character?.modified, item.character?.resourceURI, item.urls?.toMutableList(),
                        item.image, item.comicList, item.storyList, item.eventList, item.seriesList).let {
                        Log.e(TAG, "item: $it")
                        characters.add(it)
                    }
                }
                setCharacters(characters)

            }.subscribe()
    }

    private fun saveCharactersToDatabase(characters: MutableList<Character>?) {
        thread {
            characters?.forEach {
                DBConnection.database.getMarverCharactersDao()
                    .insertCharacter(it, it.thumbnail, it.comics, it.events, it.series, it.stories, it.urls)
            }
        }
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

    private fun isOnline(): Boolean {
        return try {
            val timeoutMs = 1500
            val sock = Socket()
            val sockaddr: SocketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(sockaddr, timeoutMs)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}