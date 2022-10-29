package br.com.up.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.adapter.PokeAdapter
import br.com.up.pokedex.network.PokeApi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler : RecyclerView =
            findViewById(R.id.poke_recycler)

        recycler.layoutManager = GridLayoutManager(
            this,3)

        PokeApi().getPokemons { pokemons ->

            if(pokemons != null){
                recycler.adapter = PokeAdapter(pokemons)
            }
        }

    }
}