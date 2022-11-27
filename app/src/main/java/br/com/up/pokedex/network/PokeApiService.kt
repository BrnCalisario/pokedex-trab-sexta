package br.com.up.pokedex.network


import br.com.up.pokedex.model.PokeResponse
import br.com.up.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon?limit=151")
    fun getPokemons() :
            Call<PokeResponse>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: String): Call<Pokemon>

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name : CharSequence):
            Call<PokeResponse>

}