package com.hs.newsapp.fragment.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hs.newsapp.ViewModel
import com.hs.newsapp.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    protected val binding get() = _binding!!

    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.newsListRecyclerView.adapter = NewsListAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}


/*
class NewsListFragment : BaseFragment<FragmentNewsListBinding>(
    FragmentNewsListBinding::bind,
    R.layout.fragment_news_list
), NewsListFragmentInterface {

    private val viewModel: ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.newsListRecyclerView.adapter = NewsListAdapter()
    }
}
 */