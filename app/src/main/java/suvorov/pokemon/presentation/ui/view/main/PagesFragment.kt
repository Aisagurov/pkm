package suvorov.pokemon.presentation.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.R
import suvorov.pokemon.databinding.FragmentPagesBinding
import suvorov.pokemon.presentation.ui.adapter.PagesAdapter
import suvorov.pokemon.presentation.ui.viewmodel.PagesViewModel
import suvorov.pokemon.util.Constants.POKEMON_ID
import suvorov.pokemon.util.PokemonColor
import suvorov.pokemon.util.loadImage
import suvorov.pokemon.util.setupWithViewPager

class PagesFragment: Fragment() {
    private var _binding: FragmentPagesBinding? = null
    private val binding get() = _binding!!

    private val pagesViewModel: PagesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString(POKEMON_ID))

        observeViewModel(id)

        binding.viewPager.adapter = PagesAdapter(this, id)

        binding.tabLayout.setupWithViewPager(binding.viewPager, listOf(
            requireActivity().getString(R.string.name_first_table_item),
            requireActivity().getString(R.string.name_second_table_item),
            requireActivity().getString(R.string.name_third_table_item))
        )

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel(id: String) {
        pagesViewModel.getPokemonById(id).observe(viewLifecycleOwner) {
            it?.let{ pokemon ->
                binding.toolbar.setBackgroundColor(PokemonColor(requireActivity()).getPokemonColor(pokemon.typeOfPokemon))
                activity?.window?.statusBarColor = PokemonColor(requireActivity()).getPokemonColor(pokemon.typeOfPokemon)

                binding.tabLayout.apply {
                    setTabTextColors(R.color.black, PokemonColor(requireActivity()).getPokemonColor(pokemon.typeOfPokemon))
                    setSelectedTabIndicatorColor(PokemonColor(requireActivity()).getPokemonColor(pokemon.typeOfPokemon))
                }

                binding.apply {
                    idTextView.text = pokemon.id
                    nameTextView.text = pokemon.name
                    categoryTextView.text = pokemon.category
                    imageView.loadImage(pokemon.imageUrl)
                }

                pokemon.typeOfPokemon?.apply {
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
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}