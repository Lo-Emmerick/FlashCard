package com.example.flashcards.presentation.ui.cardsInformation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.flashcards.R
import com.example.flashcards.data.model.Card
import com.example.flashcards.databinding.CardsInformationBinding
import com.example.flashcards.navigation.addInformation.cardsInformation.CardsInformationNavigation.Companion.CARD_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsInformationActivity : AppCompatActivity() {

    private lateinit var binding: CardsInformationBinding
    private val viewModel: CardsInformationViewModel by viewModel()
    private val cardId: Int by lazy { intent.getIntExtra(CARD_ID, 0) }
    private var isAnimating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = CardsInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindObserver()
        bindListener()
        viewModel.loadDeck(cardId)
    }

    private fun bindListener() {
        binding.cardFront.setOnClickListener {
            val currentCard = (viewModel.state.value as? CardsInformationState.Success)?.result
            currentCard?.let { flipFrontToBack(it.second_text, it.first_text) }
        }

        binding.cardBack.setOnClickListener {
            if (!isAnimating) {
                viewModel.showNextCard()
            }
        }

        binding.comeBack.setOnClickListener {
            finish()
        }

        binding.trash.setOnClickListener {
            viewModel.deletCard(cardId)
        }
    }

    private fun bindObserver() {
        viewModel.state.observe(this) { state ->
            setDefaultState()
            when (state) {
                CardsInformationState.Loading -> showLoadingScreen()
                CardsInformationState.Error -> showErrorScreen()
                is CardsInformationState.Success -> showCardFront(state.result)
            }
        }
    }

    private fun setDefaultState() {
        binding.cardFront.isVisible = false
        binding.cardBack.isVisible = false
        binding.stateError.root.isVisible = false
        binding.stateLoading.root.isVisible = false
    }

    private fun showErrorScreen() {
        binding.stateError.root.isVisible = true
        binding.stateError.firstText.text = "Erro ao trazer o card"
    }

    private fun showLoadingScreen() {
        binding.stateLoading.root.isVisible = true
    }

    private fun showCardFront(card: Card) {
        isAnimating = false

        binding.cardFront.apply {
            translationX = binding.root.width.toFloat()
            isVisible = true
            text = card.first_text
            rotationY = 0f
        }

        binding.cardBack.apply {
            isVisible = false
            findViewById<TextView>(R.id.card_back_top_text).text = ""
            findViewById<TextView>(R.id.card_back_bottom_text).text = ""
            rotationY = 0f
        }

        binding.cardFront.animate()
            .translationX(0f)
            .setDuration(300)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setListener(null)
            .start()
    }

    private fun flipFrontToBack(cardBackText: String, cardFrontText: String) {
        if (isAnimating) return

        isAnimating = true
        val scale = resources.displayMetrics.density
        binding.cardFront.cameraDistance = 1000 * scale
        binding.cardBack.cameraDistance = 1000 * scale

        val flipOut = ObjectAnimator.ofFloat(binding.cardFront, "rotationY", 0f, 90f)
        flipOut.duration = 200

        val flipIn = ObjectAnimator.ofFloat(binding.cardBack, "rotationY", -90f, 0f)
        flipIn.duration = 200

        flipOut.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                binding.cardFront.isVisible = false
                binding.cardBack.isVisible = true
                findViewById<TextView>(R.id.card_back_top_text).text = cardBackText
                findViewById<TextView>(R.id.card_back_bottom_text).text = cardFrontText
                flipIn.start()
            }
        })

        flipIn.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                isAnimating = false
            }
        })

        flipOut.start()
    }
}
