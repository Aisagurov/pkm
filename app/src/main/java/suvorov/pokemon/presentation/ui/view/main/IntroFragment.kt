package suvorov.pokemon.presentation.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.databinding.FragmentIntroBinding
import suvorov.pokemon.presentation.ui.viewmodel.PagesViewModel
import suvorov.pokemon.util.Constants.POKEMON_ID

class IntroFragment: Fragment() {
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    private val introViewModel: PagesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        val id = checkNotNull(arguments?.getString(POKEMON_ID))

        introViewModel.getPokemonById(id).observe(viewLifecycleOwner) {
            it.let { pokemon ->
                binding.apply {
                    heightTextView.text = pokemon.height
                    weightTextView.text = pokemon.weight
                    maleTextView.text = pokemon.malePercentage
                    femaleTextView.text = pokemon.femalePercentage
                    xdescriptionTextView.text = pokemon.xDescription
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
        fun newInstance(id: String?) = IntroFragment().apply {
            arguments = Bundle().apply {
                putString(POKEMON_ID, id)
            }
        }
    }
}