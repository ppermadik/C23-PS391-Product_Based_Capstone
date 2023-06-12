package com.dicoding.submission.capstone.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.capstone.Result
import com.dicoding.submission.capstone.databinding.FragmentNewsBinding
import com.dicoding.submission.capstone.viewmodel.NewsViewModel
import com.dicoding.submission.capstone.viewmodel.ViewModelFactory

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private lateinit var adapter: NewsAdapter
    private val binding get() = _binding!!
    private val newsViewModel: NewsViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = NewsAdapter(mutableListOf())
        recyclerView.adapter = adapter

        newsViewModel.getListNews().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    // Show loading state by making the progress bar visible
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    // Hide the progress bar
                    binding.progressBar.visibility = View.INVISIBLE

                    val newsList = result.data
                    adapter.setNewsList(newsList)
                }
                is Result.Error -> {
                    // Hide the progress bar
                    binding.progressBar.visibility = View.INVISIBLE

                    // Show error state if needed
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
