package com.hs.newsapp.fragment.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hs.newsapp.databinding.FragmentSavedNewsListBinding

class SavedNewsListFragment : Fragment() {

    private var _binding: FragmentSavedNewsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedNewsListBinding.inflate(inflater, container, false)
        val view = binding

        return view.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}