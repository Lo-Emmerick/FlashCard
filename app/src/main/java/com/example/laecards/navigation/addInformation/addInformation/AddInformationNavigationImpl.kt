package com.example.laecards.navigation.addInformation.addInformation

import android.content.Context
import android.content.Intent
import com.example.laecards.presentation.ui.addInformation.AddInformationActivity

class AddInformationNavigationImpl : AddInformationNavigation {
    override fun addInformation(context: Context): Intent {
        return Intent(context, AddInformationActivity::class.java)
    }
}