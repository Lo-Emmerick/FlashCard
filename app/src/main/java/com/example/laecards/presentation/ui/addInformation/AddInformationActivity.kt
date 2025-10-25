package com.example.laecards.presentation.ui.addInformation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.laecards.domain.data.CardEdit
import com.laecards.app.databinding.ActivityAddInformationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddInformationBinding
    private val viewModel: AddInformationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textButton.isEnabled = false

        binding.firstText.editText?.addTextChangedListener { validateFields() }
        binding.secondText.editText?.addTextChangedListener { validateFields() }

        bindListener()
        bindObserver()
    }

    private fun validateFields() {
        val firstText = binding.firstText.editText?.text.toString().trim()
        val secondText = binding.secondText.editText?.text.toString().trim()

        binding.textButton.isEnabled = firstText.isNotEmpty() && secondText.isNotEmpty()
    }

    private fun bindListener() {
        binding.textButton.setOnClickListener {
            val firstText = binding.firstText.editText?.text.toString().trim()
            val secondText = binding.secondText.editText?.text.toString().trim()
            val cardEdit = CardEdit(firstText, secondText)
            viewModel.addCard(cardEdit)
        }

        binding.comeBack.setOnClickListener {
            finish()
        }
    }

    private fun bindObserver() {
        viewModel.state.observe(this) {
            setDefaultState()
            when (it) {
                AddInformationState.Error -> showErrorScreen()
                AddInformationState.Success -> showSuccessScreen()
            }
        }
    }

    private fun setDefaultState() {
        binding.firstText.isVisible = true
        binding.secondText.isVisible = true
        binding.textButton.isVisible = true
        binding.stateError.root.isVisible = false
    }

    private fun showErrorScreen() {
        binding.firstText.isVisible = false
        binding.secondText.isVisible = false
        binding.textButton.isVisible = false
        binding.stateError.root.isVisible = true
    }

    private fun showSuccessScreen() {
        finish()
    }
}
