package com.example.lab1

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepository(
    private val dao: CharacterDao,
    private val api: CharacterApiService
) {
    fun getCharactersFromDb(): Flow<List<Character>> {
        return dao.getAllCharacters().map { entities ->
            entities.map { entity ->
                Character(
                    name = entity.name,
                    culture = entity.culture,
                    born = entity.born,
                    titles = entity.titles.split(","),
                    aliases = entity.aliases.split(","),
                    playedBy = entity.playedBy.split(",")
                )
            }
        }
    }

    suspend fun fetchCharactersFromApi(page: Int, pageSize: Int): List<Character> {
        return api.getCharacters(page, pageSize)
    }

    suspend fun saveCharactersToDb(characters: List<Character>) {
        val entities = characters.map { character ->
            CharacterEntity(
                name = character.name,
                culture = character.culture,
                born = character.born,
                titles = character.titles.joinToString(","),
                aliases = character.aliases.joinToString(","),
                playedBy = character.playedBy.joinToString(",")
            )
        }
        dao.clearCharacters()
        dao.insertCharacters(entities)
    }
}
