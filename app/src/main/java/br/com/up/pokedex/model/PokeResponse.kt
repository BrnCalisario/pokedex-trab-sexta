package br.com.up.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokeResponse(
    @SerializedName("results")
    val pokemons: List<Pokemon>
)
//
//data class PokeApiResult(
//    @SerializedName("results")
//    val pokemon: PokemonApiResult
//)


//
//data class PokeFullResponse(
//    @SerializedName("results")
//    val pokemon: PokemonFull
//)
