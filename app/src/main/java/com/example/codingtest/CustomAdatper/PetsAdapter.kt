package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codingtest.R
import com.example.codingtest.databinding.AdapterMovieBinding
import com.example.codingtest.model.Pets


class PetsAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var petList = mutableListOf<Pets.Pet>()

    @SuppressLint("NotifyDataSetChanged")
    fun setPets(pets: List<Pets.Pet>) {
        this.petList = pets.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val pet = petList[position]
            holder.binding.name.text = pet.title
            Glide.with(holder.itemView.context).load(pet.image_url).into(holder.binding.imageview)

       holder.itemView.setOnClickListener {
               findNavController(it).navigate(R.id.action_fragment1_to_fragment2,bundleOf("url" to pet.content_url))
       }
    }

    override fun getItemCount(): Int {
        return petList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) :
    RecyclerView.ViewHolder(binding.root)