package ru.spb.iac.kotlin_mobile_template.activitities.marvel.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import ru.spb.iac.kotlin_mobile_template.BR
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.CharacterDataWrapper

data class MarvelCharactersModel(private var characters: MutableList<Character>?):BaseObservable()
{
    @Bindable

    fun getCharacters(): MutableList<Character>? {
        return characters
    }
    fun setCharacters(characters: MutableList<Character>) {
        this.characters = characters
        notifyPropertyChanged(BR.characters)
    }
}
