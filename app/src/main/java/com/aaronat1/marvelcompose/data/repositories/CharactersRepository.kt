package com.aaronat1.marvelcompose.data.repositories

import com.aaronat1.marvelcompose.data.entities.Character
import com.aaronat1.marvelcompose.data.entities.Result
import com.aaronat1.marvelcompose.data.network.ApiClient

object CharactersRepository : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Result<Character> = super.find(
        id,
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}
