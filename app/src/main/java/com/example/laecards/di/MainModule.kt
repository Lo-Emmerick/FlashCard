package com.example.laecards.di

import androidx.room.Room
import com.example.laecards.data.local.database.AppDatabase
import com.example.laecards.data.repository.CardRepositoryImpl
import com.example.laecards.domain.repository.CardRepository
import com.example.laecards.domain.useCase.addCard.AddCardUseCase
import com.example.laecards.domain.useCase.addCard.AddCardUseCaseImpl
import com.example.laecards.domain.useCase.deleteCard.DeleteCardUseCase
import com.example.laecards.domain.useCase.deleteCard.DeleteCardUseCaseImpl
import com.example.laecards.domain.useCase.searchCard.SearchCardUseCase
import com.example.laecards.domain.useCase.searchCard.SearchCardUseCaseImpl
import com.example.laecards.domain.useCase.showCard.ShowCardUseCase
import com.example.laecards.domain.useCase.showCard.ShowCardUseCaseImpl
import com.example.laecards.presentation.ui.addInformation.AddInformationViewModel
import com.example.laecards.presentation.ui.cardsInformation.CardsInformationViewModel
import com.example.laecards.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loadRepositories = module {
    single { CardRepositoryImpl(dao = get()) as CardRepository }
}
val loadUseCase = module {
    single { SearchCardUseCaseImpl(repository = get()) as SearchCardUseCase }
    single { AddCardUseCaseImpl(repository = get()) as AddCardUseCase }
    single { ShowCardUseCaseImpl(repository = get()) as ShowCardUseCase }
    single { DeleteCardUseCaseImpl(repository = get()) as DeleteCardUseCase }
}
val loadViewModel = module {
    viewModel {
        HomeViewModel(searchCardUseCase = get())
    }
    viewModel {
        AddInformationViewModel(addCardUseCase = get())
    }
    viewModel {
        CardsInformationViewModel(
            showCardUseCase = get(),
            deleteCardUseCase = get()
        )
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