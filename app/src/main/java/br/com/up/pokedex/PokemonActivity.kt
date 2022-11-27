package br.com.up.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import br.com.up.pokedex.network.PokeApi
import com.squareup.picasso.Picasso

class PokemonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_view)

        var urlPokemon: String? = intent.getStringExtra("pokemon")
        var list =  urlPokemon?.split("/")


        var title: TextView = findViewById(R.id.pokemon_name)
        var type1: TextView = findViewById(R.id.textType1)

        var statHP: TextView = findViewById(R.id.textHP)
        var statATK: TextView = findViewById(R.id.textATK)
        var statDEF: TextView = findViewById(R.id.textDEF)

        var abilities: TextView = findViewById(R.id.textAbilities)
        var moves: TextView = findViewById(R.id.textMoves)


        var pokemonID: String = list!![list.size - 2]
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonID.png"
        val imageView: ImageView = findViewById(R.id.pokeImage)
        Picasso.get().load(url).into(imageView)

        PokeApi().getPokemonByID(pokemonID!!) {
            pokemon ->
                if (pokemon != null) {
                    title.text = Capitalize(pokemon.name)
                    type1.text = "Tipos: " + Capitalize(pokemon.types[0].type.name)

                    try {
                        type1.text = type1.text as String + "/" + Capitalize(pokemon.types[1].type.name)
                    } catch (e: java.lang.Exception) {}

                    statHP.text = statHP.text as String + pokemon.stats[0].base_stat
                    statATK.text = statATK.text as String + pokemon.stats[1].base_stat
                    statDEF.text = statDEF.text as String + pokemon.stats[2].base_stat

                    abilities.text =
                        abilities.text as String + " " +
                                Capitalize(pokemon.abilities[0].ability.name)

                    try {
                        abilities.text = abilities.text as String + " / " + Capitalize(pokemon.abilities[1].ability.name)
                    } catch (e: java.lang.Exception) {}

                    for(i in 0..3) {
                        try { moves.text = moves.text as String + "\n  - " + pokemon.moves[i].move.name
                        } catch (e: java.lang.Exception) { break }
                    }

//                    moves.text = moves.text as String + " " + pokemon.moves[0].move.name
//                    for(i in 1..3) {
//                        try {
//                            moves.text = moves.text as String + ", " + pokemon.moves[i].move.name
//                        } catch (e: Exception) {}
//                    }


                }
        }

    }

    fun Capitalize(string: String): String {
        return string.replaceFirstChar { it.uppercase() }
    }

}