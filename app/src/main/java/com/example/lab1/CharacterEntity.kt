package com.example.lab1

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "characters")
@TypeConverters(StringListConverter::class)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val culture: String? = null,
    val born: String? = null,
    val titles: List<String> = emptyList(),
    val aliases: List<String> = emptyList(),
    val playedBy: List<String> = emptyList()
) {
    companion object {
        fun fromDomainModel(character: Character): CharacterEntity {
            return CharacterEntity(
                name = character.name,
                culture = character.culture,
                born = character.born,
                titles = character.titles,
                aliases = character.aliases,
                playedBy = character.playedBy
            )
        }
    }

    fun toDomainModel(): Character {
        return Character(
            name = name,
            culture = culture,
            born = born,
            titles = titles,
            aliases = aliases,
            playedBy = playedBy
        )
    }
}
