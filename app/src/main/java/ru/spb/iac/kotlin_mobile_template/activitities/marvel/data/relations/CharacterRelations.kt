package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.*
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character

data class CharacterRelations(@Embedded
                              val character: Character?,
                              @Relation(parentColumn = "id", entityColumn = "id")
                              val image: Image?,
                              @Relation(parentColumn = "id", entityColumn = "id")
                              val comicList: ComicList?,
                              @Relation(parentColumn = "id", entityColumn = "id")
                              val eventList: EventList?,
                              @Relation(parentColumn = "id", entityColumn = "id")
                              val seriesList: SeriesList?,
                              @Relation(parentColumn = "id", entityColumn = "id")
                              val storyList: StoryList?,
                              @Relation(parentColumn = "id", entityColumn = "id")
                              val urls: List<Url>?)