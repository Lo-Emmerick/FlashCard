package com.example.flashcards.di

import androidx.room.Room
import com.example.flashcards.data.local.database.AppDatabase
import com.example.flashcards.data.repository.CardRepositoryImpl
import com.example.flashcards.domain.repository.CardRepository
import com.example.flashcards.domain.useCase.addCard.AddCardUseCase
import com.example.flashcards.domain.useCase.addCard.AddCardUseCaseImpl
import com.example.flashcards.domain.useCase.searchCard.SearchCardUseCase
import com.example.flashcards.domain.useCase.searchCard.SearchCardUseCaseImpl
import com.example.flashcards.domain.useCase.showCard.ShowCardUseCase
import com.example.flashcards.domain.useCase.showCard.ShowCardUseCaseImpl
import com.example.flashcards.presentation.ui.addInformation.AddInformationViewModel
import com.example.flashcards.presentation.ui.cardsInformation.CardsInformationViewModel
import com.example.flashcards.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loadRepositories = module {
    single { CardRepositoryImpl(dao = get()) as CardRepository }
}
val loadUseCase = module {
    single { SearchCardUseCaseImpl(repository = get()) as SearchCardUseCase }
    single { AddCardUseCaseImpl(repository = get()) as AddCardUseCase }
    single { ShowCardUseCaseImpl(repository = get()) as ShowCardUseCase }
}
val loadViewModel = module {
    viewModel {
        HomeViewModel(searchCardUseCase = get())
    }
    viewModel {
        AddInformationViewModel(addCardUseCase = get())
    }
    viewModel {
        CardsInformationViewModel(showCardUseCase = get())
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