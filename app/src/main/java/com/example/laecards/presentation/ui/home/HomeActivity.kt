package com.example.laecards.presentation.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.laecards.data.model.Card
import com.example.laecards.navigation.addInformation.addInformation.AddInformationNavigation
import com.example.laecards.navigation.addInformation.addInformation.AddInformationNavigationImpl
import com.example.laecards.navigation.addInformation.cardsInformation.CardsInformationNavigation
import com.example.laecards.navigation.addInformation.cardsInformation.CardsInformationNavigationImpl
import com.example.laecards.presentation.ui.home.adapter.HomeAdapter
import com.example.laecards.presentation.ui.home.adapter.HomeListener
import com.laecards.app.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), HomeListener {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val navigationAddInformation: AddInformationNavigation = AddInformationNavigationImpl()
    private val navigationCards: CardsInformationNavigation = CardsInformationNavigationImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindListener()
        bindObserver()
        viewModel.searchCard()
    }

    override fun onResume() {
        super.onResume()
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
        val intent = navigationCards.getCards(this, item.id)
        startActivity(intent)
    }
}