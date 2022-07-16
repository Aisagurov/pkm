package suvorov.pokemon.presentation.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.databinding.FragmentEvolutionBinding
import suvorov.pokemon.presentation.ui.adapter.EvolutionAdapter
import suvorov.pokemon.presentation.ui.viewmodel.PagesViewModel
import suvorov.pokemon.util.Constants.POKEMON_ID

class EvolutionFragment: Fragment() {
    private var _binding: FragmentEvolutionBinding? = null
    private val binding get() = _binding!!

    private val evolutionViewModel: PagesViewModel by viewModel()
    private val evolutionAdapter = EvolutionAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        initializeView()
    }

    private fun observeViewModel() {
        val id = checkNotNull(arguments?.getString(POKEMON_ID))

        evolutionViewModel.getPokemonById(id).observe(viewLifecycleOwner) {
            it.let { pokemon ->
                val evolutionPokemon = pokemon.evolutions ?: emptyList()
                evolutionViewModel.getEvolutionsByIds(evolutionPokemon).observe(viewLifecycleOwner){ pokemonList ->
                    evolutionAdapter.updateList(pokemonList)
                }
            }
        }
    }

    private fun initializeView() {
        binding.evolutionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = evolutionAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = EvolutionFragment().apply {
            arguments = Bundle().apply {
                putString(POKEMON_ID, id)
            }
        }
    }
}