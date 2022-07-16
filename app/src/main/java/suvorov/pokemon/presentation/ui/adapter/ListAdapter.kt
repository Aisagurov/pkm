package suvorov.pokemon.presentation.ui.adapter

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.ItemListBinding
import suvorov.pokemon.presentation.common.OnClickListener
import suvorov.pokemon.util.PokemonColor
import suvorov.pokemon.util.loadImage
import java.util.*

class ListAdapter(private val onClickListener: OnClickListener):
    RecyclerView.Adapter<ListAdapter.PokemonListViewHolder>() {

    private val pokemonList = arrayListOf<PokemonEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(
                    parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(pokemonList[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun updateList(list: List<PokemonEntity>) {
        pokemonList.clear()
        pokemonList.addAll(list)
        notifyDataSetChanged()
    }

    fun filterPokemon(list: List<PokemonEntity>, query: String) {
        pokemonList.clear()
        for (pokemon in list) {
            if (pokemon.name?.lowercase(Locale.ROOT)?.contains(query.lowercase(Locale.ROOT)) == true) {
                pokemonList.add(pokemon)
            }
        }
        notifyDataSetChanged()
    }

    class PokemonListViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: PokemonEntity, onClickListener: OnClickListener) {
            binding.idTextView.text = model.id
            binding.nameTextView.text = model.name
            binding.imageView.loadImage(model.imageUrl)

            val color = PokemonColor(itemView.context).getPokemonColor(model.typeOfPokemon)
            binding.pokemonCardView.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            binding.favoriteImageView.setImageResource(
                if (model.favorite) R.drawable.ic_baseline_donut_small_24
                else R.drawable.ic_baseline_donut_large_24
            )

            binding.favoriteImageView.setOnClickListener {
                onClickListener.onFavoriteClick(model.id)
            }

            itemView.setOnClickListener {
                onClickListener.onItemClick(model)
            }
        }
    }
}