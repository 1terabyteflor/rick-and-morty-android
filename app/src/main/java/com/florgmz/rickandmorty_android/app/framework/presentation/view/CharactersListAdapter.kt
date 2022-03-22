package com.florgmz.rickandmorty_android.app.framework.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florgmz.rickandmorty_android.R
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter

class CharactersListAdapter(private val charactersList: List<SingleCharacter?>,
                            private val onItemSelected: (SingleCharacter) -> Unit)
    : RecyclerView.Adapter<CharactersListAdapter.CharactersListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharactersListViewHolder(view)
    }

    override fun getItemCount(): Int = charactersList.size

    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        val character = charactersList[position]
        with(holder) {
            character.let {
                nameCharacter.text = character?.name
                speciesCharacter.text = character?.species

                Glide
                    .with(itemView)
                    .load(character?.image)
                    .into(imageCharacter)

                itemView.setOnClickListener { onItemSelected(character!!) }
            }
        }
    }

    class CharactersListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageCharacter: ImageView = view.findViewById(R.id.image_character)
        val nameCharacter: TextView = view.findViewById(R.id.name_character)
        val speciesCharacter: TextView = view.findViewById(R.id.species_character)
    }
}
