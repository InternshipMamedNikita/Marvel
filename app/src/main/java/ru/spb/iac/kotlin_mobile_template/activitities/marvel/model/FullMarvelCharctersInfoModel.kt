package ru.spb.iac.kotlin_mobile_template.activitities.marvel.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import ru.spb.iac.kotlin_mobile_template.BR
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.CharacterDataContainer
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.CharacterDataWrapper
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character


class FullMarvelCharctersInfoModel (private var character: Character?): BaseObservable() {
    @Bindable
    fun getCharacter() = character
    fun setCharacter(character: Character?) {
        this.character = character
        notifyPropertyChanged(BR.character)
    }
}