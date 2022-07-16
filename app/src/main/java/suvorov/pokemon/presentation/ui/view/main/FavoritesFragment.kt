package suvorov.pokemon.presentation.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.FragmentFavoritesBinding
import suvorov.pokemon.presentation.ui.adapter.ListAdapter
import suvorov.pokemon.presentation.common.OnClickListener
import suvorov.pokemon.presentation.ui.viewmodel.FavoritesViewModel
import suvorov.pokemon.util.Constants.POKEMON_ID
import suvorov.pokemon.util.Constants.POKEMON_NAME
import suvorov.pokemon.util.PokemonColor

class FavoritesFragment: Fragment(), OnClickListener {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModel()
    private var favoritesAdapter = ListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.statusBarColor = PokemonColor(view.context).convertColor(R.color.green)

        observeViewModel()

        initializeView()

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel() {
        favoritesViewModel.favoritePokemon.observe(viewLifecycleOwner) {
            favoritesAdapter.updateList(it)

            binding.favoritesTextView.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun initializeView() {
        val pokemonLayoutManager = GridLayoutManager(context, 3)
        binding.favoritesRecyclerView.apply {
            layoutManager = pokemonLayoutManager
            adapter = favoritesAdapter
        }
    }

    override fun onItemClick(model: PokemonEntity) {
        val bundle = bundleOf(POKEMON_ID to model.id, POKEMON_NAME to model.name)
        findNavController().navigate(R.id.action_favoritesFragment_to_pagesFragment, bundle, null)
    }

    override fun onFavoriteClick(id: String) {
        favoritesViewModel.updateFavoriteStatus(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}