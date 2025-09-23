package com.example.flashcards.presentation.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.flashcards.data.model.Card
import com.example.flashcards.databinding.ActivityHomeBinding
import com.example.flashcards.navigation.AddInformationNavigation
import com.example.flashcards.navigation.AddInformationNavigationImpl
import com.example.flashcards.presentation.ui.home.adapter.HomeAdapter
import com.example.flashcards.presentation.ui.home.adapter.HomeListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), HomeListener {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val navigationAddInformation: AddInformationNavigation = AddInformationNavigationImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindListener()
        bindObserver()
        viewModel.searchCard()
    }

    private fun bindListener() {
        binding.btnCenterImage.setOnClickListener {
            val intent = navigationAddInformation.addInformation(this)
            startActivity(intent)
        }
    }

    private fun bindObserver() {
        viewModel.state.observe(this) {
            setDefaultState()
            when (it) {
                HomeState.Error -> showErrorScreen()
                HomeState.Loading -> showLoadingScreen()
                HomeState.Empty -> showEmptyState()
                is HomeState.Success -> showSuccessScreen(it.result)
            }
        }
    }

    private fun setDefaultState() {
        binding.recyclerView.isVisible = false
        binding.stateError.root.isVisible = false
        binding.stateLoading.root.isVisible = false
        binding.stateEmpty.root.isVisible = false
    }

    private fun showErrorScreen() {
        binding.stateError.root.isVisible = true
    }

    private fun showLoadingScreen() {
        binding.stateLoading.root.isVisible = true
    }

    private fun showEmptyState() {
        binding.stateEmpty.root.isVisible = true
    }

    private fun showSuccessScreen(movieRoomList: List<Card>) {
        binding.recyclerView.isVisible = true
        binding.recyclerView.adapter = HomeAdapter(movieRoomList, this)
    }

    override fun onClickItem(item: Card) {
    }
}