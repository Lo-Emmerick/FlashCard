package com.example.flashcards.navigation.addInformation

import android.content.Context
import android.content.Intent
import com.example.flashcards.presentation.ui.addInformation.AddInformationActivity

class AddInformationNavigationImpl : AddInformationNavigation {
    override fun addInformation(context: Context): Intent {
        return Intent(context, AddInformationActivity::class.java)
    }
}