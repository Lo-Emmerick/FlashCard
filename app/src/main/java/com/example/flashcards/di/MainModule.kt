package com.example.flashcards.di

import androidx.room.Room
import com.example.flashcards.data.local.database.AppDatabase
import com.example.flashcards.data.repository.CardRepositoryImpl
import com.example.flashcards.domain.repository.CardRepository
import com.example.flashcards.domain.useCase.AddCardUseCase
import com.example.flashcards.domain.useCase.implementation.AddCardUseCaseImpl
import com.example.flashcards.domain.useCase.implementation.SearchCardUseCaseImpl
import com.example.flashcards.domain.useCase.`interface`.SearchCardUseCase
import com.example.flashcards.presentation.ui.addInformation.AddInformationViewModel
import com.example.flashcards.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loadRepositories = module {
    single { CardRepositoryImpl(dao = get()) as CardRepository }
}
val loadUseCase = module {
    single { SearchCardUseCaseImpl(repository = get()) as SearchCardUseCase }
    single { AddCardUseCaseImpl(repository = get()) as AddCardUseCase }

}
val loadViewModel = module {
    viewModel {
        HomeViewModel(searchCardUseCase = get())
    }
    viewModel{
        AddInformationViewModel(addCardUseCase = get())
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "card"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().cardDao() }
}