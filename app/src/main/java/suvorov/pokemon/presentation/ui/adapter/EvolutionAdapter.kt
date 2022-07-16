package suvorov.pokemon.presentation.ui.adapter

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.ItemEvolutionBinding
import suvorov.pokemon.util.PokemonColor
import suvorov.pokemon.util.loadImage

class EvolutionAdapter: RecyclerView.Adapter<EvolutionAdapter.EvolutionsViewHolder>() {

    private val pokemonList = arrayListOf<PokemonEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionsViewHolder {
        return EvolutionsViewHolder(
            ItemEvolutionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EvolutionsViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun updateList(list: List<PokemonEntity>) {
        pokemonList.clear()
        pokemonList.addAll(list)
        notifyDataSetChanged()
    }

    class EvolutionsViewHolder(private val binding: ItemEvolutionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(model: PokemonEntity) {

            binding.apply {
                idTextView.text = model.id
                nameTextView.text = model.name
                categoryTextView.text = model.category
                imageView.loadImage(model.imageUrl)
            }

            model.typeOfPokemon?.apply {
                getOrNull(0).let { firstType ->
                    binding.thirdTypeTextView.text = firstType
                    binding.thirdTypeTextView.isVisible = firstType != null
                }

                getOrNull(1).let { secondType ->
                    binding.secondTypeTextView.text = secondType
                    binding.secondTypeTextView.isVisible = secondType != null
                }

                getOrNull(2).let { thirdType ->
                    binding.firstTypeTextView.text = thirdType
                    binding.firstTypeTextView.isVisible = thirdType != null
                }
            }

            val color = PokemonColor(itemView.context).getPokemonColor(model.typeOfPokemon)
            binding.pokemonCardView.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}