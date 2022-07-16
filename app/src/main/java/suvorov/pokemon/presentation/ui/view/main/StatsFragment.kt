package suvorov.pokemon.presentation.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.databinding.FragmentStatsBinding
import suvorov.pokemon.presentation.ui.viewmodel.PagesViewModel
import suvorov.pokemon.util.Constants
import suvorov.pokemon.util.PokemonColor

class StatsFragment: Fragment() {
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    private val statesViewModel: PagesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString(Constants.POKEMON_ID))

        statesViewModel.getPokemonById(id).observe(viewLifecycleOwner) {
            it.let { pokemon ->
                binding.apply {
                    hpTextView.text = pokemon.hp.toString()
                    attackTextView.text = pokemon.attack.toString()
                    defenseTextView.text = pokemon.defense.toString()
                    specialAttackTextView.text = pokemon.specialAttack.toString()
                    specialDefenseTextView.text = pokemon.specialDefense.toString()
                    speedTextView.text = pokemon.speed.toString()

                    hpProgressbar.progress = pokemon.hp ?: 0
                    attackProgressbar.progress = pokemon.attack ?: 0
                    defenseProgressbar.progress = pokemon.defense ?: 0
                    specialAttackProgressbar.progress = pokemon.specialAttack ?: 0
                    specialDefenseProgressbar.progress = pokemon.specialDefense ?: 0
                    speedProgressbar.progress = pokemon.speed ?: 0

                    hpProgressbar.progressDrawable
                        .setTint(
                            PokemonColor(view.context)
                                .getPokemonColor(pokemon.typeOfPokemon))

                    attackProgressbar.progressDrawable
                        .setTint(
                            PokemonColor(view.context)
                                .getPokemonColor(pokemon.typeOfPokemon))

                    defenseProgressbar.progressDrawable
                        .setTint(
                            PokemonColor(view.context)
                                .getPokemonColor(pokemon.typeOfPokemon))

                    specialAttackProgressbar.progressDrawable
                        .setTint(
                            PokemonColor(view.context)
                                .getPokemonColor(pokemon.typeOfPokemon))

                    specialDefenseProgressbar.progressDrawable
                        .setTint(
                            PokemonColor(view.context)
                                .getPokemonColor(pokemon.typeOfPokemon))

                    speedProgressbar.progressDrawable
                        .setTint(
                            PokemonColor(view.context)
                                .getPokemonColor(pokemon.typeOfPokemon))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = StatsFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.POKEMON_ID, id)
            }
        }
    }
}