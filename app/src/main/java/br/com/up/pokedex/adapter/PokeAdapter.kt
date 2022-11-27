package br.com.up.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.MainActivity
import br.com.up.pokedex.R
import br.com.up.pokedex.model.Pokemon
import com.squareup.picasso.Picasso

class PokeAdapter(
    private val pokemons:List<Pokemon>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PokeViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val layout = inflater.inflate(
            R.layout.poke_item_view,
            parent,
            false)

        return PokeViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PokeViewHolder,
                                  position: Int) {

        val pokemon = pokemons[position]

        val paths = pokemon.url.split("/")
        val id = paths[paths.size - 2]
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        val imageView : ImageView = holder.itemView.findViewById(R.id.poke_image)

        Picasso.get().load(url).into(imageView)


    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    inner class PokeViewHolder(itemView:View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}