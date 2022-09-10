package com.hs.newsapp.ui.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hs.newsapp.R
import com.hs.newsapp.config.ApplicationClass
import com.hs.newsapp.config.BaseFragment
import com.hs.newsapp.databinding.FragmentCategoriesBinding
import com.hs.newsapp.model.Category
import com.hs.newsapp.ui.RecyclerViewMargin

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>(
    FragmentCategoriesBinding::bind,
    R.layout.fragment_categories
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = sharedViewModel

        binding.categoriesListRecyclerView.addItemDecoration(RecyclerViewMargin(margin = 50))
        binding.categoriesListRecyclerView.adapter = CategoriesAdapter(CategoriesListener { cate: Category ->
            sharedViewModel.onCategoryClicked(cate)
            findNavController().navigate(R.id.action_categoriesFragment_to_categoriesNewsListFragment)
        }, ApplicationClass.cateList)
    }
}