package suvorov.pokemon.presentation.ui.view.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.FragmentListBinding
import suvorov.pokemon.presentation.ui.adapter.ListAdapter
import suvorov.pokemon.presentation.common.OnClickListener
import suvorov.pokemon.presentation.ui.viewmodel.ListViewModel
import suvorov.pokemon.util.Constants.POKEMON_ID
import suvorov.pokemon.util.KeyboardHelper
import suvorov.pokemon.util.PokemonColor

class ListFragment: Fragment(), OnClickListener, SearchView.OnQueryTextListener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ListViewModel by viewModel()
    private val listAdapter = ListAdapter(this)

    private var pokemonList = listOf<PokemonEntity>()
    private var searchRequest = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.statusBarColor = PokemonColor(view.context).convertColor(R.color.green)
//        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        observeViewModel()

        initializeView()

        listViewModel.getPokemonFromApi()

        binding.pokemonSearchView.setOnQueryTextListener(this)

        initializeSearchView()

        binding.errorTextView.setOnClickListener {
            listViewModel.getPokemonFromApi()
        }

        binding.transitionFavorite.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_favoritesFragment)
        }
    }

    private fun observeViewModel() {
        listViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (listViewModel.isListEmpty() && it) View.VISIBLE else View.GONE

            if (it) binding.errorTextView.visibility = View.GONE
        }

        listViewModel.pokemonListFromDatabase.observe(viewLifecycleOwner) {
            listAdapter.filterPokemon(it, searchRequest)
            pokemonList = it

            binding.errorTextView.visibility = if (listViewModel.isListEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun initializeView() {
        val pokemonLayoutManager = GridLayoutManager(context, 3)
        binding.pokemonListRecyclerView.apply {
            layoutManager = pokemonLayoutManager
            adapter = listAdapter
        }
    }

    private fun initializeSearchView() {
        binding.pokemonSearchView.apply {
            findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
                .setHintTextColor(ContextCompat.getColor(requireActivity(), R.color.partially_white))

            findViewById<TextView>(androidx.appcompat.R.id.search_src_text).setTextColor(Color.WHITE)

            findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn).setColorFilter(Color.WHITE)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            listAdapter.filterPokemon(pokemonList, newText)
            searchRequest = newText
        }
        return true
    }

    override fun onItemClick(model: PokemonEntity) {
        val bundle = bundleOf(POKEMON_ID to model.id)
        findNavController().navigate(R.id.action_listFragment_to_pagesFragment, bundle, null)
    }

    override fun onFavoriteClick(id: String) {
        listViewModel.updateFavoriteStatus(id)
        KeyboardHelper.hideKeyboard(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}