package com.florgmz.rickandmorty_android.app.framework.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.florgmz.rickandmorty_android.R
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.app.framework.presentation.viewmodel.ListViewModel
import com.florgmz.rickandmorty_android.core.usecase.GetCharactersListUseCase
import com.florgmz.rickandmorty_android.databinding.FragmentListBinding
import org.koin.android.ext.android.inject

class ListFragment: Fragment() {
    private val getCharactersListUseCase: GetCharactersListUseCase by inject()
    private val viewModel: ListViewModel by viewModels(
        factoryProducer = { ListViewModel.ListViewModelFactory(getCharactersListUseCase)}
    )
    private var fragmentListBinding: FragmentListBinding? = null
    private val binding get() = fragmentListBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentListBinding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentListBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.getCharactersLiveData().observe(viewLifecycleOwner, { characters ->
            updateCharactersList(characters)
        })
    }

    private fun updateCharactersList(charactersList: List<SingleCharacter?>) {
        with(binding) {
            setAdapter(charactersList)
        }
    }

    private fun setAdapter(charactersList: List<SingleCharacter?>) {
        with(binding.charactersList){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CharactersListAdapter(charactersList, onItemSelected)
        }
    }

    private val onItemSelected = { character: SingleCharacter? ->
        character?.let {
            val bundle = bundleOf("id" to (character.id).toLong())
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        } ?: run {
            Toast.makeText(requireContext(), "algo salió mal", Toast.LENGTH_SHORT).show()
        }

    }
}