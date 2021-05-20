package ru.spb.iac.kotlin_mobile_template.activitities.marvel.database

import androidx.room.*
import io.reactivex.Maybe
import io.reactivex.Single
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.*
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.relations.CharacterRelations

@Dao
interface MarvelCharactersDao {

    @Transaction
    @Query("SELECT * FROM Character")
    fun getCharacters(): Single<List<CharacterRelations>>

    @Transaction
    @Query("SELECT * FROM Character WHERE id = :id")
    fun getCharacterById(id: Int): Maybe<CharacterRelations?>

    @Transaction
    @Query("SELECT * FROM Character WHERE name = :characterName")
    fun getCharacterByName(characterName: String): Maybe<CharacterRelations?>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Character?,
                        image: Image?,
                        comicList: ComicList?,
                        eventList: EventList?,
                        seriesList: SeriesList?,
                        storyList: StoryList?,
                        urls: List<Url>?)

    @Transaction
    @Update
    fun updateCharacter(character: Character?,
                        image: Image?,
                        comicList: ComicList?,
                        eventList: EventList?,
                        seriesList: SeriesList?,
                        storyList: StoryList?,
                        urls: List<Url>?)

    @Transaction
    @Delete
    fun deleteCharacter(character: Character?)

    @Transaction
    @Query("DELETE FROM Character WHERE id = :id")
    fun deleteCharacterById(id: Int)

}