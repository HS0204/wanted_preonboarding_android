package com.hs.newsapp.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hs.newsapp.databinding.LayoutCategoriesListBinding
import com.hs.newsapp.model.Category

class CategoriesAdapter(val clickListener: CategoriesListener, val cateList: ArrayList<Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.CategoriesViewHolder {
        return CategoriesViewHolder(LayoutCategoriesListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoriesViewHolder, position: Int) {
        val category = cateList[position]
        holder.bind(clickListener, category)
    }

    class CategoriesViewHolder(private var binding: LayoutCategoriesListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CategoriesListener, newsCategory: Category) {
            binding.category = newsCategory
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return cateList.size
    }
}

class CategoriesListener(val clickListener: (cate: Category) -> Unit) {
    fun onClick(cate: Category) = clickListener(cate)
}