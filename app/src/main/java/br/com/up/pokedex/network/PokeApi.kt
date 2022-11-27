package br.com.up.pokedex.network

import android.util.Log
import br.com.up.pokedex.model.PokeResponse
import br.com.up.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeApi {

    private var retrofit: Retrofit? = null
    private var service: PokeApiService? = null

    init {

        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit?.create(PokeApiService::class.java)
    }

    fun getPokemons(callback: (List<Pokemon>?) -> Unit) {

        val call = service?.getPokemons()

        call?.enqueue(object : Callback<PokeResponse> {
            override fun onResponse(call: Call<PokeResponse>, response: Response<PokeResponse>) {
                val pokemons = response.body()?.pokemons
                callback(pokemons)
            }

            override fun onFailure(call: Call<PokeResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
//
//    fun getPokemonById(callback: (Pokemon?) -> Unit, id: String) {
//
//        val call = service?.getPokemonById(id)
//
//        call?.enqueue(object : Callback<Pokemon> {
//            override fun onResponse(call: Call<PokeResponse>, response: Response<PokeResponse>) {
//                val pokemon = response.body()?.pokemons
//
//                Log.d("POKEMON_API", pokemon.toString())
//                //callback(pokemon)
//
//            }
//
//            override fun onFailure(call: Call<PokeResponse>, t: Throwable) {
//                callback(null)
//            }
//        })
//
//    }

    fun getPokemonByID(id: String, listener: (Pokemon?) -> Unit) {
        val call = service?.getPokemonById(id)

        call?.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                listener(null)
            }
        })
    }
}

