package br.com.up.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.adapter.PokeAdapter
import br.com.up.pokedex.model.Pokemon
import br.com.up.pokedex.network.PokeApi
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), PokeAdapter.OnItemClickListener {

    private var pokemonList = emptyList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler : RecyclerView =
            findViewById(R.id.poke_recycler)

        val searchButton = findViewById<Button>(R.id.search_button)
        val inputPokemon = findViewById<TextInputEditText>(R.id.poke_input_text)


        searchButton.setOnClickListener{
            if (inputPokemon.text!!.isNotEmpty())
                recycler.adapter = PokeAdapter(pokemonList.filter { it.name.contains(inputPokemon.text!!) }, this)
            else
                getPokemons(recycler)
        }
        recycler.layoutManager = GridLayoutManager(
            this,3)

        getPokemons(recycler)

    }

    private fun getPokemons(recycler: RecyclerView) {
        PokeApi().getPokemons { pokemons ->
            if(pokemons != null) {
                pokemonList = pokemons
                //pokemonList.forEach { println(it.url)}
                recycler.adapter = PokeAdapter(pokemons, this)
            }
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem: Pokemon = pokemonList[position]
        val intent = Intent(this, PokemonActivity::class.java)
        intent.putExtra("pokemon", clickedItem.url )
        startActivity(intent)

    }

}