package com.florgmz.rickandmorty_android.app.framework.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.florgmz.rickandmorty_android.app.framework.presentation.viewmodel.DetailViewModel
import com.florgmz.rickandmorty_android.core.usecase.GetSingleCharacterUseCase
import com.florgmz.rickandmorty_android.databinding.FragmentDetailBinding
import org.koin.android.ext.android.inject

class DetailFragment: Fragment() {
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase by inject()
    private val detailViewModel: DetailViewModel by viewModels (
        factoryProducer = { DetailViewModel.DetailViewModelFactory(getSingleCharacterUseCase)})
    private var fragmentDetailBinding: FragmentDetailBinding? = null
    private val binding get() = fragmentDetailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        val id = arguments?.getLong("id")
        id?.let {
            detailViewModel.getCharacter(id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDetailBinding = null
    }

    private fun setUpObserver() {
        detailViewModel.getCharacterLiveData().observe(viewLifecycleOwner, { character ->
            with(binding) {
                Glide
                    .with(requireActivity())
                    .load(character.image)
                    .into(image)

                name.text = character.name
                species.text = character.species
                gender.text = character.gender
                status.text = character.status
            }
        })

    }
}